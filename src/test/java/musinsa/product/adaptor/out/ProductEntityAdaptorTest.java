package musinsa.product.adaptor.out;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import musinsa.product.application.port.out.command.SaveProductCommand;
import musinsa.product.application.port.out.command.UpdateProductCommand;
import musinsa.product.application.port.out.dto.AllCategoryPriceSum;
import musinsa.product.domain.ProductDomain;
import musinsa.product.domain.enums.Category;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;


@ExtendWith(MockitoExtension.class)
class ProductEntityAdaptorTest {

    List<ProductEntity> productList;
    @Mock
    private ProductEntityRepository productEntityRepository;
    @Mock
    private ProductCustomRepository productCustomRepository;
    @Spy
    private ProductEntityAdaptor.ProductEntityAdaptorMapper productEntityAdaptorMapper =
            Mappers.getMapper(ProductEntityAdaptor.ProductEntityAdaptorMapper.class);
    @InjectMocks
    private ProductEntityAdaptor productEntityAdaptor;


    @BeforeEach
    void setUp() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        File jsonFile = new ClassPathResource("product/product.json").getFile();

        productList = Arrays.asList(objectMapper.readValue(jsonFile, ProductEntity[].class));
    }


    @Test
    void loadAllProductDomainList() {

        given(productEntityRepository.findAll()).willReturn(productList);

        List<ProductDomain> productDomainList = productEntityAdaptor.loadAllProductDomainList();

        assertThat(productDomainList).hasSize(72);
    }


    @Test
    void saveProductTest() {

        given(productEntityRepository.save(any(ProductEntity.class))).willReturn(
                new ProductEntity("K", Category.상의, 1000));

        ProductDomain productDomain = productEntityAdaptor.saveProduct(
                SaveProductCommand.builder().brand("K").category("상의").price(1000).build());

        assertThat(productDomain.getCategory()).isEqualTo(Category.상의);
        assertThat(productDomain.getPrice()).isEqualTo(1000);
    }


    @Test
    @DisplayName("상품 데이터가 없는 경우 테스트")
    void updateProductTest_when_product_no_exist() {

        given(productEntityRepository.findById(1L)).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> productEntityAdaptor.updateProduct(1L,
                UpdateProductCommand.builder().brand("K").category(Category.상의).price(1000).build()));

        assertThat(thrown).as("등록된 상품이 아닙니다.").isInstanceOf(NoSuchElementException.class);
    }


    @Test
    @DisplayName("브랜드만 업데이트 하는 경우 테스트")
    void updateProductTest_when_update_only_brand() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setBrand("A");
        productEntity.setCategory(Category.상의);
        productEntity.setPrice(1000);

        given(productEntityRepository.findById(1L)).willReturn(Optional.of(productEntity));

        ProductDomain productDomain =
                productEntityAdaptor.updateProduct(1L, UpdateProductCommand.builder().brand("K").build());

        assertThat(productDomain.getBrand()).isEqualTo("K");
        assertThat(productDomain.getCategory()).isEqualTo(Category.상의);
        assertThat(productDomain.getPrice()).isEqualTo(1000);

    }


    @Test
    @DisplayName("카테고리만 업데이트 하는 경우 테스트")
    void updateProductTest_when_update_only_category() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setBrand("A");
        productEntity.setCategory(Category.상의);
        productEntity.setPrice(1000);

        given(productEntityRepository.findById(1L)).willReturn(Optional.of(productEntity));

        ProductDomain productDomain =
                productEntityAdaptor.updateProduct(1L, UpdateProductCommand.builder().category(Category.바지).build());

        assertThat(productDomain.getBrand()).isEqualTo("A");
        assertThat(productDomain.getCategory()).isEqualTo(Category.바지);
        assertThat(productDomain.getPrice()).isEqualTo(1000);
    }


    @Test
    @DisplayName("상품 삭제 할 때 상품이 없는 경우 테스트")
    void deleteProductTest_when_product_no_exist() {
        given(productEntityRepository.findById(1L)).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> productEntityAdaptor.deleteProduct(1L));

        assertThat(thrown).as("등록된 상품이 아닙니다.").isInstanceOf(NoSuchElementException.class);
    }


    @Test
    void deleteProductTest() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);

        given(productEntityRepository.findById(1L)).willReturn(Optional.of(productEntity));

        willDoNothing().given(productEntityRepository).delete(productEntity);

        productEntityAdaptor.deleteProduct(1L);

        then(productEntityRepository).should(times(1)).delete(productEntity);
    }


    @Test
    void loadLowestPriceProductsByCategoryTest() {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setBrand("A");
        productEntity.setCategory(Category.상의);
        productEntity.setPrice(1000);

        given(productEntityRepository.loadLowestPriceProductsByCategory()).willReturn(List.of(productEntity));

        List<ProductDomain> productDomainList = productEntityAdaptor.loadLowestPriceProductsByCategory();

        assertThat(productDomainList).hasSize(1);
    }


    @Test
    void loadAllCategoryPriceSumByBrandTest() {

        given(productEntityRepository.loadAllCategoryPriceSumByBrand(List.of("brand"))).willReturn(
                List.of(new AllCategoryPriceSumTestClass("brand", 1000)));

        List<AllCategoryPriceSum> allCategoryPriceSumList =
                productEntityAdaptor.loadAllCategoryPriceSumByBrand(List.of("brand"));

        Assertions.assertThat(allCategoryPriceSumList).hasSize(1);
        Assertions.assertThat(allCategoryPriceSumList.get(0).getBrand()).isEqualTo("brand");
    }


    @Test
    void loadLowestPriceCategoryProductsByBrand() {

        ProductEntity shirt = new ProductEntity();
        shirt.setId(1L);
        shirt.setBrand("A");
        shirt.setCategory(Category.상의);
        shirt.setPrice(1000);

        ProductEntity pants = new ProductEntity();
        pants.setId(1L);
        pants.setBrand("A");
        pants.setCategory(Category.바지);
        pants.setPrice(2000);

        given(productEntityRepository.loadLowestPriceCategoryProductsByBrand("A")).willReturn(List.of(shirt, pants));

        List<ProductDomain> productDomainList = productEntityAdaptor.loadLowestPriceCategoryProductsByBrand("A");

        Assertions.assertThat(productDomainList).hasSize(2);
    }


    @Test
    void loadLowestPriceBrandByCategoryTest() {
        ProductEntity shirt1 = new ProductEntity();
        shirt1.setId(1L);
        shirt1.setBrand("A");
        shirt1.setCategory(Category.상의);
        shirt1.setPrice(1000);

        ProductEntity shirt2 = new ProductEntity();
        shirt2.setId(1L);
        shirt2.setBrand("C");
        shirt2.setCategory(Category.상의);
        shirt2.setPrice(1000);

        given(productCustomRepository.loadLowestPriceBrandByCategory(Category.상의)).willReturn(List.of(shirt1, shirt2));

        List<ProductDomain> productDomainList = productEntityAdaptor.loadLowestPriceBrandByCategory(Category.상의);

        Assertions.assertThat(productDomainList).hasSize(2);
    }


    @Test
    void loadHighestPriceBrandByCategoryTest() {
        ProductEntity shirt1 = new ProductEntity();
        shirt1.setId(1L);
        shirt1.setBrand("A");
        shirt1.setCategory(Category.상의);
        shirt1.setPrice(2000);

        ProductEntity shirt2 = new ProductEntity();
        shirt2.setId(1L);
        shirt2.setBrand("C");
        shirt2.setCategory(Category.상의);
        shirt2.setPrice(2000);

        given(productCustomRepository.loadHighestPriceBrandByCategory(Category.상의)).willReturn(List.of(shirt1, shirt2));

        List<ProductDomain> productDomainList = productEntityAdaptor.loadHighestPriceBrandByCategory(Category.상의);

        Assertions.assertThat(productDomainList).hasSize(2);
    }


    @Test
    void loadAllBrandIncludingAllCategoriesTest() {
        given(productCustomRepository.loadAllBrandIncludingAllCategories(Category.getCategoryCount())).willReturn(
                List.of("A", "B"));

        List<String> brandList = productEntityAdaptor.loadAllBrandIncludingAllCategories(Category.getCategoryCount());

        Assertions.assertThat(brandList).hasSize(2);
        Assertions.assertThat(brandList).contains("A", "B");
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