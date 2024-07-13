package musinsa.product.adaptor.in.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;
import musinsa.product.domain.enums.Category;

@Value
public class EnrollProductDto {

    @NotBlank String brand;
    @NotBlank Category category;
    @Positive long price;


    @Builder
    private EnrollProductDto(String brand, Category category, long price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
