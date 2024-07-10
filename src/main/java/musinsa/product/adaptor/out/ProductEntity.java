package musinsa.product.adaptor.out;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Table(name = "product")
@NoArgsConstructor
@DynamicUpdate
class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String category;
    private long price;


    public ProductEntity(String brand, String category, int price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
