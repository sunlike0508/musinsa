package musinsa.product.adaptor.in.dto;


import lombok.Builder;
import lombok.Value;
import musinsa.product.domain.enums.Category;

@Value
public class UpdateProductDto {

    String brand;
    Category category;
    long price;


    @Builder
    private UpdateProductDto(String brand, Category category, long price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
