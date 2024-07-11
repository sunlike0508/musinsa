package musinsa.product.application.port.in;

import java.util.List;
import musinsa.product.application.port.in.dto.AdminProductDto;
import musinsa.product.application.port.in.dto.LowestPriceProductByCategory;

public interface GetProductUseCase {

    List<AdminProductDto> getProductList();

    LowestPriceProductByCategory getLowestPriceProductsByCategory();
}
