package musinsa.application.port.out;

import java.util.List;
import musinsa.application.port.out.command.SaveProductCommand;
import musinsa.domain.ProductDomain;

public interface ProductRepositoryPort {

    List<ProductDomain> loadAllProductDomainList();

    void saveProductList(List<SaveProductCommand> saveProductCommandList);
}
