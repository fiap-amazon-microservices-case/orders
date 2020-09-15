package br.com.fiap.aoj.orders.interfaces.converters;

import br.com.fiap.aoj.orders.domain.OrderDomain;
import br.com.fiap.aoj.orders.interfaces.dtos.OrderDto;
import br.com.fiap.aoj.orders.interfaces.dtos.ProductDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Lazy
@Component
public class OrderDomainToOrderDtoConverter implements Converter<OrderDomain, OrderDto> {

	private final ProductDomainToProductDtoConverter productDomainToProductDtoConverter;

	public OrderDomainToOrderDtoConverter(final ProductDomainToProductDtoConverter productDomainToProductDtoConverter) {
		this.productDomainToProductDtoConverter = productDomainToProductDtoConverter;
	}

	@Override
	public OrderDto convert(final OrderDomain source) {
		final OrderDto orderDto = new OrderDto();
		orderDto.setId(source.getOrderId().toString());
		orderDto.setClientId(source.getClientId());
		orderDto.setClosed(source.isclosed());
		orderDto.setCreatedAt(source.createdAt());
		orderDto.setTotal(source.getTotal());
		orderDto.setProducts(buildProducts(source));

		return orderDto;
	}

	private Set<ProductDto> buildProducts(final OrderDomain source) {
		return source.getProducts().stream() //
				.map(productDomainToProductDtoConverter::convert) //
				.collect(Collectors.toSet());
	}
}