package musinsa.data;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brand;
    private String category;
    private long price;

    protected Product() {

    }

    public Product(String brand, String category, int price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
