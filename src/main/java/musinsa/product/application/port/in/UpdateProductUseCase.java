package musinsa.product.application.port.in;

import musinsa.product.adaptor.in.dto.UpdateProductDto;
import musinsa.product.application.port.in.dto.AdminProductDto;

public interface UpdateProductUseCase {


    AdminProductDto updateProduct(long id, UpdateProductDto updateProductDto);
}
