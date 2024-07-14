package musinsa.product.application.port.out;

import java.util.List;
import musinsa.product.application.port.out.dto.AllCategoryPriceSum;
import musinsa.product.domain.ProductDomain;
import musinsa.product.domain.enums.Category;

public interface LoadProductPort {

    List<ProductDomain> loadAllProductDomainList();

    List<ProductDomain> loadLowestPriceProductsByCategory();

    List<AllCategoryPriceSum> loadAllCategoryPriceSumByBrand(List<String> brandList);

    List<ProductDomain> loadLowestPriceCategoryProductsByBrand(String brand);

    List<ProductDomain> loadLowestPriceBrandByCategory(Category category);

    List<ProductDomain> loadHighestPriceBrandByCategory(Category category);

    List<String> loadAllBrandIncludingAllCategories(int categoryCount);
}
