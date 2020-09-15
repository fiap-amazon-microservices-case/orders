package br.com.fiap.aoj.orders.interfaces.converters;

import br.com.fiap.aoj.orders.domain.OrderDomain;
import br.com.fiap.aoj.orders.domain.ProductDomain;
import br.com.fiap.aoj.orders.interfaces.dtos.OrderDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Lazy
@Component
public class OrderDtoToOrderDomainConverter implements Converter<OrderDto, OrderDomain> {

	private final ProductDtoToProductDomainConverter productDtoToProductDomainConverter;

	public OrderDtoToOrderDomainConverter(final ProductDtoToProductDomainConverter productDtoToProductDomainConverter) {
		this.productDtoToProductDomainConverter = productDtoToProductDomainConverter;
	}

	@Override
	public OrderDomain convert(final OrderDto source) {
		return OrderDomain.builder() //
					.clientId(source.getClientId()) //
					.products(buildProducts(source)) //
					.builder();
	}

	private Set<ProductDomain> buildProducts(final OrderDto source) {
		return source.getProducts().stream() //
				.map(productDtoToProductDomainConverter::convert) //
				.collect(Collectors.toSet());
	}
}
