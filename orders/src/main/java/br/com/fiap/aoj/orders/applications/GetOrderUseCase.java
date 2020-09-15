package br.com.fiap.aoj.orders.applications;

import br.com.fiap.aoj.orders.data.OrderRepository;
import br.com.fiap.aoj.orders.domain.OrderDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetOrderUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetOrderUseCase.class);

	private final OrderRepository orderRepository;

	public GetOrderUseCase(final OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Optional<OrderDomain> get(final UUID orderId){
		try{
			LOGGER.debug("m=get(orderId={})", orderId);

			return orderRepository.findById(orderId);
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause={})", exception.getMessage(), exception);
			return Optional.empty();
		}
	}
}