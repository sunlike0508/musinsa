package musinsa.product.adaptor.in;


import lombok.RequiredArgsConstructor;
import musinsa.product.application.port.in.GetProductUseCase;
import musinsa.product.application.port.in.dto.LowestPriceProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserProductController {

    private final GetProductUseCase getProductUseCase;


    @GetMapping("products/categories/lowest-price-products")
    public ResponseEntity<LowestPriceProductDto> getLowestPriceProductsByCategory() {

        LowestPriceProductDto lowestPriceProductDto = getProductUseCase.getLowestPriceProductsByCategory();

        return new ResponseEntity<>(lowestPriceProductDto, HttpStatus.OK);
    }


}
