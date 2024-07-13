package musinsa.product.application.port.in.dto;

import lombok.Builder;
import lombok.Value;
import musinsa.product.domain.enums.Category;

@Value
public class AdminProductDto {

    long id;
    String brand;
    Category category;
    long price;


    @Builder
    private AdminProductDto(long id, String brand, Category category, long price) {
        this.id = id;
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
