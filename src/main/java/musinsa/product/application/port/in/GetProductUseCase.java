package musinsa.product.application.port.in;

import java.util.List;
import musinsa.product.application.port.in.dto.AdminProductDto;
import musinsa.product.application.port.in.dto.LowestPriceProductDto;

public interface GetProductUseCase {

    List<AdminProductDto> getProductList();

    LowestPriceProductDto getLowestPriceProductsByCategory();
}
