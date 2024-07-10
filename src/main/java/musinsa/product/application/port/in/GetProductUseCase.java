package musinsa.product.application.port.in;

import java.util.List;
import musinsa.product.application.port.in.dto.ProductDto;

public interface GetProductUseCase {

    List<ProductDto> getProductList();
}
