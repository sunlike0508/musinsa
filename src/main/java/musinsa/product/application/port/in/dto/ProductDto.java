package musinsa.product.application.port.in.dto;

import lombok.Builder;
import lombok.Value;

@Value
public class ProductDto {

    String brand;
    String category;
    long price;


    @Builder
    private ProductDto(String brand, String category, long price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
