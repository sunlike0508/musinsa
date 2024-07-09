package musinsa.application.port.out;

import java.util.List;
import musinsa.domain.ProductDomain;

public interface ProductRepositoryPort {

    List<ProductDomain> loadAllProductDomainList();
}
