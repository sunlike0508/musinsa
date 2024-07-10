package musinsa.product.domain;

import lombok.Builder;
import lombok.Value;

@Value
public class ProductDomain {

    long id;
    String brand;
    String category;
    long price;


    @Builder
    private ProductDomain(long id, String brand, String category, long price) {
        this.id = id;
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
