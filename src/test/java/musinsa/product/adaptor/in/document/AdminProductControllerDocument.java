package musinsa.product.adaptor.in.document;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import org.springframework.test.web.servlet.ResultHandler;

public class AdminProductControllerDocument {

    public static ResultHandler getProductList() {
        return document("get-products-list", responseFields(fieldWithPath("[].id").description("상품 ID"),
                fieldWithPath("[].brand").description("브랜드"), fieldWithPath("[].category").description("카테고리"),
                fieldWithPath("[].price").description("상품 가격")));
    }


    public static ResultHandler enrollProduct() {
        return document("enroll-product",
                requestFields(fieldWithPath("brand").description("브랜드"), fieldWithPath("category").description("카테고리"),
                        fieldWithPath("price").description("상품 가격")),
                responseFields(fieldWithPath("id").description("상품 ID"), fieldWithPath("brand").description("브랜드"),
                        fieldWithPath("category").description("카테고리"), fieldWithPath("price").description("상품 가격")));
    }


    public static ResultHandler updateProduct() {
        return document("update-product",
                requestFields(fieldWithPath("brand").description("브랜드"), fieldWithPath("category").description("카테고리"),
                        fieldWithPath("price").description("상품 가격")),
                responseFields(fieldWithPath("id").description("상품 ID"), fieldWithPath("brand").description("브랜드"),
                        fieldWithPath("category").description("카테고리"), fieldWithPath("price").description("상품 가격")));
    }


    public static ResultHandler deleteProduct() {
        return document("delete-product", pathParameters(parameterWithName("id").description("상품 ID")));
    }
}
