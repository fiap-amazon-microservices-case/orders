package br.com.fiap.aoj.orders.interfaces.dtos;

public class ShippingDto {

	public static final ShippingDto of(final Double calculatedShipping){
		final ShippingDto shippingDto = new ShippingDto();
		shippingDto.setCalculatedShipping(calculatedShipping);
		return shippingDto;
	}

	private Double calculatedShipping;

	public Double getCalculatedShipping() {
		return calculatedShipping;
	}

	public void setCalculatedShipping(Double calculatedShipping) {
		this.calculatedShipping = calculatedShipping;
	}
}
