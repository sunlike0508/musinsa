package musinsa.product.adaptor.in.dto;


import lombok.Builder;
import lombok.Value;

@Value
public class EnrollProductDto {

    String brand;
    String category;
    long price;


    @Builder
    private EnrollProductDto(String brand, String category, long price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
