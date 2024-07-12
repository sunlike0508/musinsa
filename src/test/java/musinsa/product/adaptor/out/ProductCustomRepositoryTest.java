package musinsa.product.adaptor.out;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import musinsa.config.TestQueryDslConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;


@DataJpaTest
@Import({TestQueryDslConfig.class, ProductCustomRepository.class})
class ProductCustomRepositoryTest {

    @Autowired
    private ProductEntityRepository productEntityRepository;

    @Autowired
    private ProductCustomRepository productCustomRepository;


    @BeforeEach
    void setUp() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        File jsonFile = new ClassPathResource("product/product.json").getFile();

        List<ProductEntity> productList = Arrays.asList(objectMapper.readValue(jsonFile, ProductEntity[].class));

        productEntityRepository.saveAll(productList);
    }


    @Test
    void loadLowestPriceBrandByCategoryTest() {

        List<ProductEntity> productEntityList = productCustomRepository.loadLowestPriceBrandByCategory("스니커즈");

        Assertions.assertThat(productEntityList).hasSize(2);
    }


    @Test
    void loadHighestPriceBrandByCategoryTest() {

        List<ProductEntity> productEntityList = productCustomRepository.loadHighestPriceBrandByCategory("스니커즈");

        Assertions.assertThat(productEntityList).hasSize(1);
        Assertions.assertThat(productEntityList.get(0).getPrice()).isEqualTo(9900);
    }
}