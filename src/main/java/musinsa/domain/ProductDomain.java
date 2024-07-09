package musinsa.domain;

import lombok.Builder;
import lombok.Value;

@Value
public class ProductDomain {
    String brand;
    String category;
    long price;


    @Builder
    private ProductDomain(String brand, String category, long price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
