package musinsa.product.application.port.in;

import java.util.List;
import musinsa.product.application.port.in.dto.AdminProductDto;
import musinsa.product.application.port.in.dto.LowestHighestPriceBrandDto;
import musinsa.product.application.port.in.dto.LowestPriceProductDto;
import musinsa.product.application.port.in.dto.LowestPriceSaleBrandDto;
import musinsa.product.domain.enums.Category;

public interface GetProductUseCase {

    List<AdminProductDto> getProductList();

    LowestPriceProductDto getLowestPriceProductsByCategory();

    LowestPriceSaleBrandDto getLowestPriceCategoryProductsByBrand();

    LowestHighestPriceBrandDto getLowestHighestPriceBrandByCategory(Category category);
}
