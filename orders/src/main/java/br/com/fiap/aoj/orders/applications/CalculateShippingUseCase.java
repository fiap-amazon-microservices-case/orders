package br.com.fiap.aoj.orders.applications;

import br.com.fiap.aoj.orders.data.OrderRepository;
import br.com.fiap.aoj.orders.domain.OrderDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class CalculateShippingUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetOrderUseCase.class);

	private static final Random RANDOM = new Random();

	private final OrderRepository orderRepository;

	public CalculateShippingUseCase(final OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Double calculate(final UUID orderId, final String zipCode){
		LOGGER.debug("m=calculate(orderId={}, zipCode={})", orderId, zipCode);

		//FIXME: Implementar regras de cálculo de frente, este é apenas um resultado para fins acadêmicos.
		return orderRepository.findById(orderId)
				.map(OrderDomain::getProducts) //
				.map(p -> p.size() * RANDOM.nextInt(10)) //
				.map(Double::new) //
				.orElseThrow(() -> new IllegalArgumentException("Falha ao calcular o frente"));

	}

}
