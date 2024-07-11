package musinsa.product.application.port.in.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;


@Value
public class LowestPriceProductDto {


    List<LowestPriceProduct> productList;
    long totalPrice;


    @Builder
    private LowestPriceProductDto(List<LowestPriceProduct> productList, long totalPrice) {
        this.productList = List.copyOf(productList);
        this.totalPrice = totalPrice;
    }


    public List<LowestPriceProduct> getProductList() {
        return List.copyOf(productList);
    }


    @Value
    public static class LowestPriceProduct {

        String brand;
        String category;
        long price;


        @Builder
        private LowestPriceProduct(String brand, String category, long price) {
            this.brand = brand;
            this.category = category;
            this.price = price;
        }
    }
}
