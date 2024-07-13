package musinsa.product.adaptor.in.document;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import org.springframework.test.web.servlet.ResultHandler;

public class ProductControllerDocument {

    public static ResultHandler getLowestPriceProductsByCategory() {
        return document("get-lowest-price-products-by-category",
                responseFields(subsectionWithPath("productList").description("<<products-list, 상품>> 리스트"),
                        fieldWithPath("totalPrice").description("상품 총액")),
                responseFields(beneathPath("productList").withSubsectionId("productList"),
                        fieldWithPath("brand").description("브랜드"), fieldWithPath("category").description("카테고리"),
                        fieldWithPath("price").description("상품 가격")));
    }


    public static ResultHandler getLowestPriceCategoryProductsByBrand() {
        return document("get-lowest-price-category-products-by-brand", responseFields(
                        subsectionWithPath("lowestPriceSaleBrand").description("<<lowest-price-sale-brand, 최저가 판매 브랜드>>")),
                responseFields(beneathPath("lowestPriceSaleBrand").withSubsectionId("lowest-price-sale-brand"),
                        fieldWithPath("brand").description("브랜드"),
                        subsectionWithPath("categories").description("<<lowest-price-categories, 최저가 판매 카테고리>> 리스트"),
                        fieldWithPath("totalPrice").description("상품 총액")),
                responseFields(beneathPath("lowestPriceSaleBrand.categories").withSubsectionId("categories"),
                        fieldWithPath("category").description("카테고리"), fieldWithPath("price").description("상품 가격")));
    }


    public static ResultHandler getLowestHighestPriceBrandByCategory() {
        return document("get-lowest-highest-price-brand-by-category",
                responseFields(fieldWithPath("category").description("카테고리"),
                        subsectionWithPath("lowestPrices").description("<<lowest-price-brand, 최저가 브랜드>> 리스트"),
                        subsectionWithPath("highestPrices").description("<<highest-price-brand, 최고가 브랜드>> 리스트")),
                responseFields(beneathPath("lowestPrices").withSubsectionId("lowest-price-brand"),
                        fieldWithPath("brand").description("브랜드"), fieldWithPath("price").description("상품 가격")),
                responseFields(beneathPath("highestPrices").withSubsectionId("highest-price-brand"),
                        fieldWithPath("brand").description("브랜드"), fieldWithPath("price").description("상품 가격")));
    }
}
