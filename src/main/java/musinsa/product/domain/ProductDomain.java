package musinsa.product.domain;

import lombok.Builder;
import lombok.Value;
import musinsa.product.domain.enums.Category;

@Value
public class ProductDomain {

    long id;
    String brand;
    Category category;
    long price;


    @Builder
    private ProductDomain(long id, String brand, Category category, long price) {
        this.id = id;
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
