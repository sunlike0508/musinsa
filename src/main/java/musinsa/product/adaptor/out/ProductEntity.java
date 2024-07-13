package musinsa.product.adaptor.out;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import musinsa.product.domain.enums.Category;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Table(name = "products")
@NoArgsConstructor
@DynamicUpdate
class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    @Enumerated(EnumType.STRING)
    private Category category;
    private long price;


    public ProductEntity(String brand, Category category, int price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }
}
