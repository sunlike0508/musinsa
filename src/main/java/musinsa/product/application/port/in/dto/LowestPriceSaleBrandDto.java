package musinsa.product.application.port.in.dto;


import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
public class LowestPriceSaleBrandDto {

    LowestPriceSaleBrand lowestPriceSaleBrand;


    @Builder
    private LowestPriceSaleBrandDto(LowestPriceSaleBrand lowestPriceSaleBrand) {
        this.lowestPriceSaleBrand = lowestPriceSaleBrand;
    }


    @Value
    public static class LowestPriceSaleBrand {

        String brand;
        List<CategoryProduct> categories;
        long totalPrice;


        @Builder
        private LowestPriceSaleBrand(String brand, List<CategoryProduct> categories, long totalPrice) {
            this.brand = brand;
            this.categories = List.copyOf(categories);
            this.totalPrice = totalPrice;
        }


        public List<CategoryProduct> getCategories() {
            return List.copyOf(categories);
        }


        @Value
        public static class CategoryProduct {

            String category;
            long price;


            @Builder
            private CategoryProduct(String category, long price) {
                this.category = category;
                this.price = price;
            }
        }
    }
}
