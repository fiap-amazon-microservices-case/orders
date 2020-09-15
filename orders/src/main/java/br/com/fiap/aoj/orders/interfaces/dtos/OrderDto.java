package br.com.fiap.aoj.orders.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public class OrderDto {

	@JsonProperty(access = READ_ONLY)
	private String id;

	private String clientId;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime createdAt;

	@JsonProperty(access = READ_ONLY)
	private Boolean isClosed;

	private Set<ProductDto> products = new HashSet<>();

	private Double total;

	public String getId() {
		return id;
	}

	public String getClientId() {
		return clientId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@JsonProperty("isClosed")
	public Boolean getClosed() {
		return isClosed;
	}

	public Set<ProductDto> getProducts() {
		return products;
	}

	public Double getTotal() {
		return total;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setClosed(Boolean closed) {
		isClosed = closed;
	}

	public void setProducts(Set<ProductDto> products) {
		this.products = products;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}
