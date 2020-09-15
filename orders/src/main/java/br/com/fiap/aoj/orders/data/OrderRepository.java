package br.com.fiap.aoj.orders.data;

import br.com.fiap.aoj.orders.domain.OrderDomain;
import br.com.fiap.aoj.orders.domain.ProductDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;
import java.util.stream.Stream;

public interface OrderRepository extends MongoRepository<OrderDomain, UUID> {

	public Stream<OrderDomain> findByClientIdOrderByCreatedAtDesc(final String clientId);

}