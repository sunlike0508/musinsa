package musinsa.product.adaptor.in;


import lombok.RequiredArgsConstructor;
import musinsa.product.application.port.in.GetProductUseCase;
import musinsa.product.application.port.in.dto.LowestHighestPriceBrandDto;
import musinsa.product.application.port.in.dto.LowestPriceProductDto;
import musinsa.product.application.port.in.dto.LowestPriceSaleBrandDto;
import musinsa.product.domain.enums.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductCodiController {

    private final GetProductUseCase getProductUseCase;


    /**
     * 카테고리 별 최저 가격 브랜드 조회 API
     *
     * @return LowestPriceProductDto
     */
    @GetMapping("products/categories/lowest-price-products")
    public ResponseEntity<LowestPriceProductDto> getLowestPriceProductsByCategory() {

        LowestPriceProductDto lowestPriceProductDto = getProductUseCase.getLowestPriceProductsByCategory();

        return new ResponseEntity<>(lowestPriceProductDto, HttpStatus.OK);
    }


    /**
     * 단일 브랜드로 모든 카테고리 구매할 경우 최저 가격으로 판매하는 브랜드의 상품들 조회
     *
     * @return LowestPriceSaleBrandDto
     */
    @GetMapping("products/brands/lowest-price-brand")
    public ResponseEntity<LowestPriceSaleBrandDto> getLowestPriceCategoryProductsByBrand() {

        LowestPriceSaleBrandDto lowestPriceProductDto = getProductUseCase.getLowestPriceCategoryProductsByBrand();

        return new ResponseEntity<>(lowestPriceProductDto, HttpStatus.OK);
    }


    /**
     * 원하는 카테고리의 최저, 최고 가격 상품 조회
     *
     * @param category 검색할 카테고리명
     * @return LowestHighestPriceBrandDto
     */
    @GetMapping("products/lowest-highest-price-brand")
    public ResponseEntity<LowestHighestPriceBrandDto> getLowestHighestPriceBrandByCategory(
            @RequestParam(name = "category") Category category) {

        LowestHighestPriceBrandDto lowestHighestPriceBrandDto =
                getProductUseCase.getLowestHighestPriceBrandByCategory(category);

        return new ResponseEntity<>(lowestHighestPriceBrandDto, HttpStatus.OK);
    }
}
