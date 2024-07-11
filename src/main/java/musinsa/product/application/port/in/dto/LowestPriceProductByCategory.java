package musinsa.product.application.port.in.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;


@Value
public class LowPriceProductByCategory {

    List<LowPriceProduct> productList;
    long totalPrice;


    @Builder
    private LowPriceProductByCategory(List<LowPriceProduct> productList, long totalPrice) {
        this.productList = List.copyOf(productList);
        this.totalPrice = totalPrice;
    }


    public List<LowPriceProduct> getProductList() {
        return List.copyOf(productList);
    }


    @Value
    public static class LowPriceProduct {

        String brand;
        String category;
        long price;


        @Builder
        private LowPriceProduct(String brand, String category, long price) {
            this.brand = brand;
            this.category = category;
            this.price = price;
        }
    }
}
