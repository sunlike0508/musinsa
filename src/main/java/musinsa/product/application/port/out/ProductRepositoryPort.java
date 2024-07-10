package musinsa.product.application.port.out;

import java.util.List;
import musinsa.product.application.port.out.command.SaveProductCommand;
import musinsa.product.domain.ProductDomain;

public interface ProductRepositoryPort {

    List<ProductDomain> loadAllProductDomainList();

    void saveProductList(List<SaveProductCommand> saveProductCommandList);
}
