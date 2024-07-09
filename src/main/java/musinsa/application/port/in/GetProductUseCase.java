package musinsa.application.port.in;

import java.util.List;
import musinsa.application.port.in.dto.ProductDto;

public interface GetProductUseCase {

    List<ProductDto> getProductList();
}
