package musinsa.product.application.port.in;

import java.util.List;
import musinsa.product.application.port.in.dto.AdminProductDto;

public interface GetProductUseCase {

    List<AdminProductDto> getProductList();
}
