package br.com.fiap.aoj.orders.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class ProductDomain implements Serializable {

	private static final long serialVersionUID = -9175533454350356130L;

	private UUID productId;

	private String categoryId;

	private String name;

	private PriceDomain price;

	private Integer amount;

	private ProductDomain(final Builder builder){
		this.productId = builder.id;
		this.categoryId = builder.categoryId;
		this.name = builder.name;
		this.amount = builder.amount;
		this.price = builder.price;
	}

	//Construtor padrão para serialização do mongo
	public ProductDomain(){}

	public UUID getProductId() {
		return productId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public String getName() {
		return name;
	}

	public Integer getAmount() {
		return amount;
	}

	public PriceDomain getPrice() {
		return price;
	}

	public static final CategoryId builder(){
		return new Builder();
	}

	public static final class Builder implements CategoryId, Name, Price, Build{
		private static final Integer DEFAULT_AMOUNT = 1;
		private UUID id = UUID.randomUUID();
		private String categoryId;
		private String name;
		private String description;
		private PriceDomain price;
		private Integer amount = DEFAULT_AMOUNT;

		@Override
		public Build id(final UUID id) {
			this.id = id;
			return this;
		}

		@Override
		public Name categoryId(final String categoryId) {
			this.categoryId = categoryId;
			return this;
		}

		@Override
		public Price name(final String name) {
			this.name = name;
			return this;
		}

		@Override
		public Build amount(final Integer amount) {
			this.amount = amount;
			return this;
		}

		@Override
		public Build price(final PriceDomain priceDomain) {
			this.price = priceDomain;
			return this;
		}

		@Override
		public ProductDomain builder() {
			return new ProductDomain(this);
		}
	}

	public interface CategoryId{
		public Name categoryId(final String categoryId);
	}

	public interface Name{
		public Price name(final String name);
	}

	public interface Price{
		public Build price(final PriceDomain priceDomain);
	}

	public interface Build{
		public Build amount(final Integer amount);
		public Build id(final UUID id);
		public ProductDomain builder();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ProductDomain that = (ProductDomain) o;
		return Objects.equals(productId, that.productId) && Objects.equals(categoryId, that.categoryId) && Objects
				.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, categoryId, name);
	}
}
