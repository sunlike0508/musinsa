package musinsa.product.adaptor.in;


import java.util.List;
import lombok.RequiredArgsConstructor;
import musinsa.product.adaptor.in.dto.EnrollProductDto;
import musinsa.product.application.port.in.EnrollProductUseCase;
import musinsa.product.application.port.in.GetProductUseCase;
import musinsa.product.application.port.in.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final GetProductUseCase getProductUseCase;
    private final EnrollProductUseCase enrollProductUseCase;


    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProductList() {
        return new ResponseEntity<>(getProductUseCase.getProductList(), HttpStatus.OK);
    }


    @PostMapping("/product")
    public ResponseEntity<ProductDto> enrollProduct(@RequestBody EnrollProductDto enrollProductDto) {

        ProductDto productDto = enrollProductUseCase.enrollProduct(enrollProductDto);

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}
