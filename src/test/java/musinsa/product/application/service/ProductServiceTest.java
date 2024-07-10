package musinsa.product.application.service;

import java.util.List;
import musinsa.product.application.port.in.dto.ProductDto;
import musinsa.product.application.port.out.ProductRepositoryPort;
import musinsa.product.domain.ProductDomain;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Spy
    private ProductService.ProductServiceMapper productServiceMapper =
            Mappers.getMapper(ProductService.ProductServiceMapper.class);


    @InjectMocks
    private ProductService productService;


    @Test
    void getProductListTest() {

        ProductDomain productDomain1 = ProductDomain.builder().brand("A").category("상의").price(1000).build();
        ProductDomain productDomain2 = ProductDomain.builder().brand("A").category("바지").price(2000).build();

        given(productRepositoryPort.loadAllProductDomainList()).willReturn(List.of(productDomain1, productDomain2));

        List<ProductDto> productList = productService.getProductList();

        Assertions.assertThat(productList).hasSize(2);
    }
}