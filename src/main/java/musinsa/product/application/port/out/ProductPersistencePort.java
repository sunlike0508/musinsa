package musinsa.product.application.port.out;

import java.util.List;
import musinsa.product.application.port.out.command.SaveProductCommand;
import musinsa.product.application.port.out.command.UpdateProductCommand;
import musinsa.product.domain.ProductDomain;

public interface ProductPersistencePort {

    List<ProductDomain> loadAllProductDomainList();

    ProductDomain saveProduct(SaveProductCommand saveProductCommand);

    void saveProductList(List<SaveProductCommand> saveProductCommandList);

    ProductDomain updateProduct(long id, UpdateProductCommand updateProductCommand);

    void deleteProduct(long id);
}
