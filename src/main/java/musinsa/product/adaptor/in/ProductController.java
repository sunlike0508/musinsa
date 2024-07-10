package musinsa.product.adaptor.in;


import java.util.List;
import lombok.RequiredArgsConstructor;
import musinsa.product.application.port.in.GetProductUseCase;
import musinsa.product.application.port.in.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final GetProductUseCase getProductUseCase;


    @GetMapping("/exception")
    public ResponseEntity<List<ProductDto>> exception() throws Exception {

        throw new Exception("오류 테스트");
    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProductList() {
        return new ResponseEntity<>(getProductUseCase.getProductList(), HttpStatus.OK);
    }

}
