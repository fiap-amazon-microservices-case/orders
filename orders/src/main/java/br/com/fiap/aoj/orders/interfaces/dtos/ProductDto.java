package br.com.fiap.aoj.orders.interfaces.dtos;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDto {

	@NotNull(message = "Campo obrigatório")
	private String id;

	@NotNull(message = "Campo obrigatório")
	private String categoryId;

	@NotNull(message = "Campo obrigatório")
	@Size(min = 2, max = 64, message = "Campo deve ter entre {min} e {max} caracteres.")
	private String name;

	@Valid
	@NotNull(message = "Campo obrigatório")
	private PriceDto price;

	@NotNull(message = "Campo obrigatório")
	private Integer amount;

	public String getId() {
		return id;
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

	public PriceDto getPrice() {
		return price;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(PriceDto price) {
		this.price = price;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
