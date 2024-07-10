package musinsa.product.application.port.in;

import musinsa.product.adaptor.in.dto.EnrollProductDto;
import musinsa.product.application.port.in.dto.ProductDto;

public interface EnrollProductUseCase {

    ProductDto enrollProduct(EnrollProductDto enrollProductDto);
}
