package musinsa.product.application.port.out.command;

import lombok.Builder;
import lombok.Value;

@Value
public class UpdateProductCommand {

    String brand;
    String category;
    long price;


    @Builder
    private UpdateProductCommand(String brand, String category, long price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
