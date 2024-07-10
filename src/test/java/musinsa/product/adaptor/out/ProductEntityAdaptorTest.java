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
import musinsa.product.domain.ProductDomain;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;


@ExtendWith(MockitoExtension.class)
class ProductEntityAdaptorTest {

    List<ProductEntity> productList;
    @Mock
    private ProductEntityRepository productEntityRepository;
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

        given(productEntityRepository.save(any(ProductEntity.class))).willReturn(new ProductEntity("K", "상의", 1000));

        ProductDomain productDomain = productEntityAdaptor.saveProduct(
                SaveProductCommand.builder().brand("K").category("상의").price(1000).build());

        assertThat(productDomain.getCategory()).isEqualTo("상의");
        assertThat(productDomain.getPrice()).isEqualTo(1000);
    }


    @Test
    @DisplayName("상품 데이터가 없는 경우 테스트")
    void updateProductTest_when_product_no_exist() {

        given(productEntityRepository.findById(1L)).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> productEntityAdaptor.updateProduct(1L,
                UpdateProductCommand.builder().brand("K").category("상의").price(1000).build()));

        assertThat(thrown).as("등록된 상품이 아닙니다.\"").isInstanceOf(NoSuchElementException.class);
    }


    @Test
    @DisplayName("브랜드만 업데이트 하는 경우 테스트")
    void updateProductTest_when_update_only_brand() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBrand("A");
        productEntity.setCategory("상의");
        productEntity.setPrice(1000);

        given(productEntityRepository.findById(1L)).willReturn(Optional.of(productEntity));
        
        ProductDomain productDomain =
                productEntityAdaptor.updateProduct(1L, UpdateProductCommand.builder().brand("K").build());

        assertThat(productDomain.getBrand()).isEqualTo("K");
        assertThat(productDomain.getCategory()).isEqualTo("상의");
        assertThat(productDomain.getPrice()).isEqualTo(1000);

    }


    @Test
    @DisplayName("카테고리만 업데이트 하는 경우 테스트")
    void updateProductTest_when_update_only_category() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBrand("A");
        productEntity.setCategory("상의");
        productEntity.setPrice(1000);

        given(productEntityRepository.findById(1L)).willReturn(Optional.of(productEntity));

        ProductDomain productDomain =
                productEntityAdaptor.updateProduct(1L, UpdateProductCommand.builder().category("바지").build());

        assertThat(productDomain.getBrand()).isEqualTo("A");
        assertThat(productDomain.getCategory()).isEqualTo("바지");
        assertThat(productDomain.getPrice()).isEqualTo(1000);
    }
}