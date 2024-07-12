package musinsa.product.adaptor.out;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import musinsa.product.application.port.out.dto.AllCategoryPriceSum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;


@DataJpaTest
class ProductEntityRepositoryTest {


    @Autowired
    private ProductEntityRepository productEntityRepository;


    @BeforeEach
    void setUp() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        File jsonFile = new ClassPathResource("product/product.json").getFile();

        List<ProductEntity> productList = Arrays.asList(objectMapper.readValue(jsonFile, ProductEntity[].class));

        productEntityRepository.saveAll(productList);
    }


    @Test
    void findAll() {
        List<ProductEntity> productList = productEntityRepository.findAll();

        Assertions.assertThat(productList).hasSize(72);
        Assertions.assertThat(productList.get(0).getBrand()).isEqualTo("A");
        Assertions.assertThat(productList.get(0).getCategory()).isEqualTo("상의");
        Assertions.assertThat(productList.get(0).getPrice()).isEqualTo(11200);

    }


    @Test
    @DisplayName("카테고리 별 최저 가격 상품 조회")
    void loadLowestPriceProductsByCategoryTest() {

        List<ProductEntity> productEntityList = productEntityRepository.loadLowestPriceProductsByCategory();

        Assertions.assertThat(productEntityList).hasSize(8);

        Assertions.assertThat(productEntityList.stream().mapToLong(ProductEntity::getPrice).sum()).isEqualTo(34100);
    }


    @Test
    @DisplayName("단일 브랜드 모든 카테고리 상품을 구매할 때 최저 가격 브랜드 상품들 조회")
    void loadSumLowestPriceBrandProducts() {

        List<AllCategoryPriceSum> allCategoryPriceSumList = productEntityRepository.loadAllCategoryPriceSumByBrand();

        Assertions.assertThat(allCategoryPriceSumList).hasSize(9);

        AllCategoryPriceSum allCategoryPriceSum = allCategoryPriceSumList.get(0);

        Assertions.assertThat(allCategoryPriceSum.getBrand()).isEqualTo("D");
    }


    @Test
    @DisplayName("D 브랜드로 모든 카테고리 최저 상품 조회")
    void loadLowestPriceProductsByBrandTest() {
        List<ProductEntity> productEntityList = productEntityRepository.loadLowestPriceProductsByBrand("D");

        Assertions.assertThat(productEntityList).hasSize(8);

        Assertions.assertThat(productEntityList.stream().mapToLong(ProductEntity::getPrice).sum()).isEqualTo(36100);
    }


    @Test
    @DisplayName("D브랜드 악세사리 최저 가격 상품 추가 후 브랜드로 모든 카테고리 최저 상품 조회")
    void loadLowestPriceProductsByBrandTest_after_add_min_price_product() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBrand("D");
        productEntity.setCategory("악세사리");
        productEntity.setPrice(1000);

        productEntityRepository.save(productEntity);

        List<ProductEntity> productEntityList = productEntityRepository.loadLowestPriceProductsByBrand("D");

        Assertions.assertThat(productEntityList).hasSize(8);

        Assertions.assertThat(productEntityList.stream().mapToLong(ProductEntity::getPrice).sum()).isEqualTo(35100);
    }
}