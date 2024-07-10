package musinsa.product.adaptor.in.dto;


import lombok.Builder;
import lombok.Value;

@Value
public class UpdateProductDto {

    String brand;
    String category;
    long price;


    @Builder
    private UpdateProductDto(String brand, String category, long price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
