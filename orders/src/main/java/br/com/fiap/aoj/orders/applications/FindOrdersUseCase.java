package br.com.fiap.aoj.orders.applications;

import br.com.fiap.aoj.orders.data.OrderRepository;
import br.com.fiap.aoj.orders.domain.OrderDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class FindOrdersUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FindOrdersUseCase.class);

	private final OrderRepository orderRepository;

	public FindOrdersUseCase(final OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Stream<OrderDomain> find(final String clientId){
		try{
			LOGGER.debug("m=find(clientId={})", clientId);

			return orderRepository.findByClientIdOrderByCreatedAtDesc(clientId);
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause={})", exception.getMessage(), exception);
			return Stream.empty();
		}
	}
}