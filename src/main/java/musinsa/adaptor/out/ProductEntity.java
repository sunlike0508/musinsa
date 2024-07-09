package musinsa.adaptor.out;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "product")
class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brand;
    private String category;
    private long price;


    protected ProductEntity() {

    }


    public ProductEntity(String brand, String category, int price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
