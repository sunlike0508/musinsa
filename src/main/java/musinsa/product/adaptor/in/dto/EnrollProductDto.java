package musinsa.product.adaptor.in.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

@Value
public class EnrollProductDto {

    @NotBlank String brand;
    @NotBlank String category;
    @Positive long price;


    @Builder
    private EnrollProductDto(String brand, String category, long price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
