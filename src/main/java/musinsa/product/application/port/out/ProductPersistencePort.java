package musinsa.product.application.port.out;

import java.util.List;
import musinsa.product.application.port.out.command.SaveProductCommand;
import musinsa.product.application.port.out.command.UpdateProductCommand;
import musinsa.product.application.port.out.dto.AllCategoryPriceSum;
import musinsa.product.domain.ProductDomain;
import musinsa.product.domain.enums.Category;

public interface ProductPersistencePort {

    List<ProductDomain> loadAllProductDomainList();

    ProductDomain saveProduct(SaveProductCommand saveProductCommand);

    void saveProductList(List<SaveProductCommand> saveProductCommandList);

    ProductDomain updateProduct(long id, UpdateProductCommand updateProductCommand);

    void deleteProduct(long id);

    List<ProductDomain> loadLowestPriceProductsByCategory();

    List<AllCategoryPriceSum> loadAllCategoryPriceSumByBrand(List<String> brandList);

    List<ProductDomain> loadLowestPriceCategoryProductsByBrand(String brand);

    List<ProductDomain> loadLowestPriceBrandByCategory(Category category);

    List<ProductDomain> loadHighestPriceBrandByCategory(Category category);

    List<String> loadAllBrandIncludingAllCategories(int categoryCount);
}
