package musinsa.product.application.port.in.dto;


import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
public class LowestHighestPriceBrandDto {

    String category;
    List<BrandProduct> lowestPrices;
    List<BrandProduct> highestPrices;


    @Builder
    private LowestHighestPriceBrandDto(String category, List<BrandProduct> lowestPrices,
            List<BrandProduct> highestPrices) {
        this.category = category;
        this.lowestPrices = List.copyOf(lowestPrices);
        this.highestPrices = List.copyOf(highestPrices);
    }


    public List<BrandProduct> getLowestPrices() {
        return List.copyOf(lowestPrices);
    }


    public List<BrandProduct> getHighestPrices() {
        return List.copyOf(highestPrices);
    }


    @Value
    public static class BrandProduct {

        String brand;
        long price;


        @Builder
        private BrandProduct(String brand, long price) {
            this.brand = brand;
            this.price = price;
        }
    }
}
