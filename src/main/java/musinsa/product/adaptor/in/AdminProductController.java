package musinsa.product.adaptor.in;


import java.util.List;
import lombok.RequiredArgsConstructor;
import musinsa.product.adaptor.in.dto.EnrollProductDto;
import musinsa.product.adaptor.in.dto.UpdateProductDto;
import musinsa.product.application.port.in.DeleteProductUseCase;
import musinsa.product.application.port.in.EnrollProductUseCase;
import musinsa.product.application.port.in.GetProductUseCase;
import musinsa.product.application.port.in.UpdateProductUseCase;
import musinsa.product.application.port.in.dto.AdminProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminProductController {

    private final GetProductUseCase getProductUseCase;
    private final EnrollProductUseCase enrollProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;


    @GetMapping("/products")
    public ResponseEntity<List<AdminProductDto>> getProductList() {
        return new ResponseEntity<>(getProductUseCase.getProductList(), HttpStatus.OK);
    }


    /**
     * 상품 등록 API
     *
     * @param enrollProductDto 상품 등록할 내용 DTO
     * @return 등록된 상품 DTO
     */

    @PostMapping("/product")
    public ResponseEntity<AdminProductDto> enrollProduct(@RequestBody EnrollProductDto enrollProductDto) {

        AdminProductDto adminProductDto = enrollProductUseCase.enrollProduct(enrollProductDto);

        return new ResponseEntity<>(adminProductDto, HttpStatus.OK);
    }


    /**
     * 상품 업데이트 API
     *
     * @param id               상품 ID
     * @param updateProductDto 업데이트 할 상품 내용 DTO
     * @return : 업데이트한 상품 DTO
     */

    @PatchMapping("/products/{id}")
    public ResponseEntity<AdminProductDto> updateProduct(@PathVariable(name = "id") long id,
            @RequestBody UpdateProductDto updateProductDto) {

        AdminProductDto adminProductDto = updateProductUseCase.updateProduct(id, updateProductDto);

        return new ResponseEntity<>(adminProductDto, HttpStatus.OK);
    }


    /**
     * 상품 삭제 API
     *
     * @param id 상품 ID
     * @return empty response
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<AdminProductDto> deleteProduct(@PathVariable(name = "id") long id) {

        deleteProductUseCase.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
