package musinsa.product.application.port.in;

import musinsa.product.adaptor.in.dto.UpdateProductDto;
import musinsa.product.application.port.in.dto.ProductDto;

public interface UpdateProductUseCase {


    ProductDto updateProduct(long id, UpdateProductDto updateProductDto);
}
