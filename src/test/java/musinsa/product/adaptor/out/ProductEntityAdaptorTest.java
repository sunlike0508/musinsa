package musinsa.product.adaptor.out;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import musinsa.product.application.port.out.command.SaveProductCommand;
import musinsa.product.domain.ProductDomain;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

        Assertions.assertThat(productDomainList).hasSize(72);
    }


    @Test
    void saveProductTest() {

        given(productEntityRepository.save(any(ProductEntity.class))).willReturn(new ProductEntity("K", "상의", 1000));

        ProductDomain productDomain = productEntityAdaptor.saveProduct(
                SaveProductCommand.builder().brand("K").category("상의").price(1000).build());

        Assertions.assertThat(productDomain.getCategory()).isEqualTo("상의");
        Assertions.assertThat(productDomain.getPrice()).isEqualTo(1000);
    }
}