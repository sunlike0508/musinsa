package musinsa.product.adaptor.out;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        productEntityList.forEach(productEntity -> System.out.println(
                productEntity.getCategory() + " " + productEntity.getBrand() + " " + productEntity.getPrice()));

        Assertions.assertThat(productEntityList).hasSize(8);

        Assertions.assertThat(productEntityList.stream().mapToLong(ProductEntity::getPrice).sum()).isEqualTo(34100);
    }
}