package musinsa.product.adaptor.in;

import java.util.List;
import com.google.gson.Gson;
import musinsa.config.RestDocsConfiguration;
import musinsa.product.adaptor.in.document.AdminProductControllerDocument;
import musinsa.product.adaptor.in.dto.EnrollProductDto;
import musinsa.product.adaptor.in.dto.UpdateProductDto;
import musinsa.product.application.port.in.DeleteProductUseCase;
import musinsa.product.application.port.in.EnrollProductUseCase;
import musinsa.product.application.port.in.GetProductUseCase;
import musinsa.product.application.port.in.UpdateProductUseCase;
import musinsa.product.application.port.in.dto.AdminProductDto;
import musinsa.product.domain.enums.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureRestDocs
@ImportAutoConfiguration(RestDocsConfiguration.class)
@WebMvcTest(controllers = AdminProductController.class)
class AdminProductControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private GetProductUseCase getProductUseCase;

    @MockBean
    private EnrollProductUseCase enrollProductUseCase;

    @MockBean
    private UpdateProductUseCase updateProductUseCase;

    @MockBean
    private DeleteProductUseCase deleteProductUseCase;


    @Test
    @DisplayName("상품 전체 조회 API")
    void getProductListTest() throws Exception {

        AdminProductDto adminProductDto =
                AdminProductDto.builder().id(1).brand("A").category(Category.상의).price(10000).build();

        given(getProductUseCase.getProductList()).willReturn(List.of(adminProductDto));

        this.mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(AdminProductControllerDocument.getProductList());
    }


    @Test
    @DisplayName("상품 등록 API")
    void enrollProductTest() throws Exception {
        EnrollProductDto enrollProductDto =
                EnrollProductDto.builder().brand("A").category(Category.상의).price(10000).build();

        AdminProductDto adminProductDto =
                AdminProductDto.builder().id(1).brand("A").category(Category.상의).price(10000).build();

        given(enrollProductUseCase.enrollProduct(enrollProductDto)).willReturn(adminProductDto);

        this.mockMvc.perform(
                        post("/products").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(enrollProductDto)))
                .andExpect(status().isOk()).andDo(AdminProductControllerDocument.enrollProduct());
    }


    @Test
    @DisplayName("상품 수정 API")
    void updateProduct() throws Exception {
        UpdateProductDto updateProductDto =
                UpdateProductDto.builder().brand("A").category(Category.상의).price(10000).build();

        AdminProductDto adminProductDto =
                AdminProductDto.builder().id(1).brand("A").category(Category.상의).price(10000).build();

        given(updateProductUseCase.updateProduct(1, updateProductDto)).willReturn(adminProductDto);

        this.mockMvc.perform(patch("/products/{id}", 1).contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(updateProductDto))).andExpect(status().isOk())
                .andDo(AdminProductControllerDocument.updateProduct());
    }


    @Test
    @DisplayName("상품 삭제 API")
    void deleteProduct() throws Exception {

        willDoNothing().given(deleteProductUseCase).deleteProduct(1);

        this.mockMvc.perform(delete("/products/{id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(AdminProductControllerDocument.deleteProduct());
    }
}