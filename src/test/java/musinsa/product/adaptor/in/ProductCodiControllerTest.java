package musinsa.product.adaptor.in;

import java.util.List;
import musinsa.config.RestDocsConfiguration;
import musinsa.product.adaptor.in.document.ProductControllerDocument;
import musinsa.product.application.port.in.GetProductUseCase;
import musinsa.product.application.port.in.dto.LowestHighestPriceBrandDto;
import musinsa.product.application.port.in.dto.LowestPriceProductDto;
import musinsa.product.application.port.in.dto.LowestPriceSaleBrandDto;
import musinsa.product.domain.enums.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureRestDocs
@ImportAutoConfiguration(RestDocsConfiguration.class)
@WebMvcTest(controllers = ProductCodiController.class)
class ProductCodiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private GetProductUseCase getProductUseCase;


    @Test
    @DisplayName("카테고리 별 최저 가격 브랜드 조회 API")
    void getLowestPriceProductsByCategoryTest() throws Exception {

        LowestPriceProductDto.LowestPriceProduct lowestPriceProduct =
                LowestPriceProductDto.LowestPriceProduct.builder().brand("A").category("상의").price(10000).build();

        LowestPriceProductDto lowestPriceProductDto =
                LowestPriceProductDto.builder().productList(List.of(lowestPriceProduct)).totalPrice(10000).build();

        given(getProductUseCase.getLowestPriceProductsByCategory()).willReturn(lowestPriceProductDto);

        this.mockMvc.perform(get("/products/categories/lowest-price-products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(ProductControllerDocument.getLowestPriceProductsByCategory());
    }


    @Test
    @DisplayName("단일 브랜드로 모든 카테고리 구매할 경우 최저 가격으로 판매하는 브랜드의 상품들 조회")
    void getLowestPriceCategoryProductsByBrandTest() throws Exception {

        LowestPriceSaleBrandDto.LowestPriceSaleBrand.CategoryProduct categoryProduct =
                LowestPriceSaleBrandDto.LowestPriceSaleBrand.CategoryProduct.builder().category("상의").price(10000)
                        .build();

        LowestPriceSaleBrandDto.LowestPriceSaleBrand lowestPriceSaleBrand =
                LowestPriceSaleBrandDto.LowestPriceSaleBrand.builder().brand("A").categories(List.of(categoryProduct))
                        .totalPrice(10000).build();

        LowestPriceSaleBrandDto lowestPriceProductDto =
                LowestPriceSaleBrandDto.builder().lowestPriceSaleBrand(lowestPriceSaleBrand).build();

        given(getProductUseCase.getLowestPriceCategoryProductsByBrand()).willReturn(lowestPriceProductDto);

        this.mockMvc.perform(get("/products/brands/lowest-price-brand").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(ProductControllerDocument.getLowestPriceCategoryProductsByBrand());
    }


    @Test
    @DisplayName("원하는 카테고리의 최저, 최고 가격 상품 조회")
    void getLowestHighestPriceBrandByCategoryTest() throws Exception {

        LowestHighestPriceBrandDto.BrandProduct highestPriceBrandProduct =
                LowestHighestPriceBrandDto.BrandProduct.builder().brand("A").price(10000).build();

        LowestHighestPriceBrandDto.BrandProduct lowestPriceBrandProduct =
                LowestHighestPriceBrandDto.BrandProduct.builder().brand("A").price(1000).build();

        LowestHighestPriceBrandDto lowestHighestPriceBrandDto =
                LowestHighestPriceBrandDto.builder().highestPrices(List.of(highestPriceBrandProduct))
                        .lowestPrices(List.of(lowestPriceBrandProduct)).category(Category.상의).build();

        given(getProductUseCase.getLowestHighestPriceBrandByCategory(Category.상의)).willReturn(
                lowestHighestPriceBrandDto);

        this.mockMvc.perform(get("/products/lowest-highest-price-brand").param("category", "상의")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(ProductControllerDocument.getLowestHighestPriceBrandByCategory());
    }

}