package br.com.fiap.aoj.orders.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderDomain implements Serializable {

	private static final long serialVersionUID = 464080420223903195L;

	@MongoId
	private UUID orderId;

	@Indexed
	private String clientId;

	private LocalDateTime createdAt;

	private Boolean isClosed;

	private Set<ProductDomain> products;

	private OrderDomain(final Builder builder){
		this.orderId = builder.orderId;
		this.clientId = builder.clientId;
		this.createdAt = builder.createdAt;
		this.isClosed = builder.isClosed;
		this.products = builder.products;
	}

	//Construtor padrão para serialização do mongo
	public OrderDomain(){}

	public UUID getOrderId() {
		return orderId;
	}

	public String getClientId() {
		return clientId;
	}

	public LocalDateTime createdAt() {
		return createdAt;
	}

	public Boolean isclosed() {
		return isClosed;
	}

	public Set<ProductDomain> getProducts() {
		return new HashSet<>(products);
	}

	public void add(final ProductDomain productDomain){
		this.products.add(productDomain);
	}

	public Double getTotal(){
		return products.stream() //
				.map(p -> p.getAmount() * p.getPrice().getPrice()) //
				.collect(Collectors.summingDouble(Double::doubleValue));
	}

	public static final ClientId builder(){
		return new Builder();
	}

	public static final class Builder implements ClientId, Products, Build{
		private UUID orderId = UUID.randomUUID();
		private String clientId;
		private LocalDateTime createdAt = LocalDateTime.now();
		private Boolean isClosed = Boolean.FALSE;
		private Set<ProductDomain> products = Collections.emptySet();

		@Override
		public Build orderId(final String orderId) {
			this.orderId = UUID.fromString(orderId);
			return this;
		}

		@Override
		public Products clientId(final String clientId) {
			this.clientId = clientId;
			return this;
		}

		@Override
		public Build createdAt(final LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		@Override
		public Build isClosed(final Boolean isClosed) {
			this.isClosed = isClosed;
			return this;
		}

		@Override
		public Build products(final Set<ProductDomain> products) {
			this.products = products;
			return this;
		}

		@Override
		public OrderDomain builder() {
			return new OrderDomain(this);
		}
	}

	public interface ClientId{
		public Products clientId(final String clientId);
	}

	public interface Products{
		public Build products(final Set<ProductDomain> products);
	}

	public interface Build{
		public Build orderId(final String orderId);
		public Build createdAt(final LocalDateTime createdAt);
		public Build isClosed(final Boolean isClosed);
		public OrderDomain builder();
	}
}