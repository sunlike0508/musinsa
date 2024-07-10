package musinsa.product.application.port.in.dto;

import lombok.Builder;
import lombok.Value;

@Value
public class AdminProductDto {

    long id;
    String brand;
    String category;
    long price;


    @Builder
    private AdminProductDto(long id, String brand, String category, long price) {
        this.id = id;
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
