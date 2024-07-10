package musinsa.product.application.port.in;

import musinsa.product.adaptor.in.dto.EnrollProductDto;
import musinsa.product.application.port.in.dto.AdminProductDto;

public interface EnrollProductUseCase {

    AdminProductDto enrollProduct(EnrollProductDto enrollProductDto);
}
