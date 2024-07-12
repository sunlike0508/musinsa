package musinsa.product.application.service;

import java.util.List;
import musinsa.product.adaptor.in.dto.EnrollProductDto;
import musinsa.product.adaptor.in.dto.UpdateProductDto;
import musinsa.product.application.port.in.dto.AdminProductDto;
import musinsa.product.application.port.in.dto.LowestPriceProductDto;
import musinsa.product.application.port.in.dto.LowestPriceSaleBrandDto;
import musinsa.product.application.port.out.ProductPersistencePort;
import musinsa.product.application.port.out.command.SaveProductCommand;
import musinsa.product.application.port.out.command.UpdateProductCommand;
import musinsa.product.application.port.out.dto.AllCategoryPriceSum;
import musinsa.product.domain.ProductDomain;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductPersistencePort productPersistencePort;

    @Spy
    private ProductService.ProductServiceMapper productServiceMapper =
            Mappers.getMapper(ProductService.ProductServiceMapper.class);


    @InjectMocks
    private ProductService productService;


    @Test
    void getProductListTest() {

        ProductDomain productDomain1 = ProductDomain.builder().brand("A").category("상의").price(1000).build();
        ProductDomain productDomain2 = ProductDomain.builder().brand("A").category("바지").price(2000).build();

        given(productPersistencePort.loadAllProductDomainList()).willReturn(List.of(productDomain1, productDomain2));

        List<AdminProductDto> productList = productService.getProductList();

        assertThat(productList).hasSize(2);
    }


    @Test
    void getLowestPriceProductsByCategoryTest() {
        ProductDomain productDomain1 = ProductDomain.builder().brand("A").category("상의").price(1000).build();
        ProductDomain productDomain2 = ProductDomain.builder().brand("A").category("바지").price(2000).build();

        given(productPersistencePort.loadLowestPriceProductsByCategory()).willReturn(
                List.of(productDomain1, productDomain2));

        LowestPriceProductDto lowestPriceProductDto = productService.getLowestPriceProductsByCategory();

        assertThat(lowestPriceProductDto.getTotalPrice()).isEqualTo(3000);
    }


    @Test
    void getLowestPriceCategoryProductsByBrand() {

        given(productPersistencePort.loadAllCategoryPriceSumByBrand()).willReturn(
                List.of(new AllCategoryPriceSumTestClass("brand1", 1000),
                        new AllCategoryPriceSumTestClass("brand2", 2000)));

        given(productPersistencePort.loadLowestPriceCategoryProductsByBrand("brand1")).willReturn(
                List.of(ProductDomain.builder().category("상의").price(400).build(),
                        ProductDomain.builder().category("바지").price(600).build()));

        LowestPriceSaleBrandDto lowestPriceSaleBrandDto = productService.getLowestPriceCategoryProductsByBrand();

        Assertions.assertThat(lowestPriceSaleBrandDto.getLowestPriceSaleBrand().getTotalPrice()).isEqualTo(1000);
        Assertions.assertThat(lowestPriceSaleBrandDto.getLowestPriceSaleBrand().getBrand()).isEqualTo("brand1");
        Assertions.assertThat(lowestPriceSaleBrandDto.getLowestPriceSaleBrand().getCategories()).hasSize(2);
    }


    @Test
    void enrollProductTest() {
        ProductDomain productDomain = ProductDomain.builder().brand("A").category("상의").price(1000).build();

        given(productPersistencePort.saveProduct(any(SaveProductCommand.class))).willReturn(productDomain);

        AdminProductDto adminProductDto =
                productService.enrollProduct(EnrollProductDto.builder().brand("A").category("상의").price(1000).build());

        assertThat(adminProductDto.getBrand()).isEqualTo("A");
    }


    @Test
    void updateProductTest() {
        ProductDomain productDomain = ProductDomain.builder().category("바지").build();

        given(productPersistencePort.updateProduct(eq(1L), any(UpdateProductCommand.class))).willReturn(productDomain);

        AdminProductDto adminProductDto = productService.updateProduct(1L,
                UpdateProductDto.builder().brand("A").category("상의").price(1000).build());

        assertThat(adminProductDto.getCategory()).isEqualTo("바지");
    }


    @Test
    void deleteProductTest() {

        willDoNothing().given(productPersistencePort).deleteProduct(1L);

        productService.deleteProduct(1);

        then(productPersistencePort).should(times(1)).deleteProduct(1L);
    }


    private static class AllCategoryPriceSumTestClass implements AllCategoryPriceSum {

        private final String brand;
        private final long totalPrice;


        public AllCategoryPriceSumTestClass(String brand, long totalPrice) {
            this.brand = brand;
            this.totalPrice = totalPrice;
        }


        @Override
        public String getBrand() {
            return brand;
        }


        @Override
        public long getTotalPrice() {
            return totalPrice;
        }
    }
}