package musinsa.product.application.port.out.command;

import lombok.Builder;
import lombok.Value;
import musinsa.product.domain.enums.Category;

@Value
public class UpdateProductCommand {

    String brand;
    Category category;
    long price;


    @Builder
    private UpdateProductCommand(String brand, Category category, long price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
