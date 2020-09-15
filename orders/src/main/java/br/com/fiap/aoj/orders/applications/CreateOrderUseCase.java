package br.com.fiap.aoj.orders.applications;

import br.com.fiap.aoj.orders.data.OrderRepository;
import br.com.fiap.aoj.orders.domain.OrderDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateOrderUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateOrderUseCase.class);

	private final OrderRepository orderRepository;

	public CreateOrderUseCase(final OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Optional<OrderDomain> create(final OrderDomain orderDomain){
		try{
			LOGGER.debug("m=create(orderDomain={})", orderDomain);

			orderRepository.insert(orderDomain);
			return Optional.of(orderDomain);
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause={})", exception.getMessage(), exception);
			return Optional.empty();
		}
	}

}