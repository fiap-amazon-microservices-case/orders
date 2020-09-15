package br.com.fiap.aoj.orders.interfaces;

import br.com.fiap.aoj.orders.applications.CalculateShippingUseCase;
import br.com.fiap.aoj.orders.applications.CreateOrderUseCase;
import br.com.fiap.aoj.orders.applications.FindOrdersUseCase;
import br.com.fiap.aoj.orders.applications.GetOrderUseCase;
import br.com.fiap.aoj.orders.domain.OrderDomain;
import br.com.fiap.aoj.orders.interfaces.converters.OrderDomainToOrderDtoConverter;
import br.com.fiap.aoj.orders.interfaces.converters.OrderDtoToOrderDomainConverter;
import br.com.fiap.aoj.orders.interfaces.dtos.OrderDto;
import br.com.fiap.aoj.orders.interfaces.dtos.ShippingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "${api.version.v1:/v1}/orders")
class OrdersController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrdersController.class);

	private final CreateOrderUseCase createOrderUseCase;
	private final GetOrderUseCase getOrderUseCase;
	private final FindOrdersUseCase findOrdersUseCase;
	private final CalculateShippingUseCase calculateShippingUseCase;
	private final OrderDtoToOrderDomainConverter orderDtoToOrderDomainConverter;
	private final OrderDomainToOrderDtoConverter orderDomainToOrderDtoConverter;

	OrdersController(final CreateOrderUseCase createOrderUseCase,
			final GetOrderUseCase getOrderUseCase,
			final FindOrdersUseCase findOrdersUseCase,
			final CalculateShippingUseCase calculateShippingUseCase,
			final OrderDtoToOrderDomainConverter orderDtoToOrderDomainConverter,
			final OrderDomainToOrderDtoConverter orderDomainToOrderDtoConverter) {
		this.createOrderUseCase = createOrderUseCase;
		this.getOrderUseCase = getOrderUseCase;
		this.findOrdersUseCase = findOrdersUseCase;
		this.calculateShippingUseCase = calculateShippingUseCase;
		this.orderDtoToOrderDomainConverter = orderDtoToOrderDomainConverter;
		this.orderDomainToOrderDtoConverter = orderDomainToOrderDtoConverter;
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Mono<OrderDto> create(@RequestBody @Valid final OrderDto orderDto){
		LOGGER.debug("m=create(OrderDto={})", orderDto);

		final OrderDomain orderDomain = orderDtoToOrderDomainConverter.convert(orderDto);
		final Optional<OrderDomain> optionalOfOrderDomain = createOrderUseCase.create(orderDomain);

		return optionalOfOrderDomain.map(orderDomainToOrderDtoConverter::convert)//
				.map(Mono::just) //
				.orElseThrow(() -> new RuntimeException());
	}

	@GetMapping("{id}")
	@ResponseStatus(OK)
	public Mono<OrderDto> get(@PathVariable("id") String id){
		LOGGER.debug("m=get(id={})", id);

		return getOrderUseCase.get(UUID.fromString(id)) //
				.map(orderDomainToOrderDtoConverter::convert)//
				.map(Mono::just) //
				.orElseThrow(() -> new IllegalArgumentException());
	}

	@GetMapping
	@ResponseStatus(OK)
	public Flux<OrderDto> find(
			@RequestParam(name = "clientId") final String clientId){
		LOGGER.debug("m=find(clientId={})", clientId);

		return Flux.fromStream(findOrdersUseCase.find(clientId) //
				.map(orderDomainToOrderDtoConverter::convert));
	}

	@GetMapping("{orderId}/shipping")
	@ResponseStatus(OK)
	public Mono<ShippingDto> calculateShipping(
			@PathVariable("orderId") final String orderId,
			@RequestParam("zipCode") final String zipCode){
		LOGGER.debug("m=calculateShipping(orderId={})", orderId);

		final Double calculatedShipping = calculateShippingUseCase.calculate(UUID.fromString(orderId), zipCode);
		return Mono.just(ShippingDto.of(calculatedShipping));
	}
}