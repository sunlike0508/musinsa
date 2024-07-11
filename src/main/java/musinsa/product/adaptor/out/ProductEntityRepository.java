package musinsa.product.adaptor.out;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "select id, brand, category, price from (SELECT *, (rank() over(partition by category"
            + " order by price asc, brand desc)) as rank FROM product) where rank = 1", nativeQuery = true)
    List<ProductEntity> loadLowestPriceProductsByCategory();
}