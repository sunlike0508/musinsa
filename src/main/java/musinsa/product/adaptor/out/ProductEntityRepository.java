package musinsa.product.adaptor.out;

import java.util.List;
import musinsa.product.application.port.out.dto.AllCategoryPriceSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "select id, brand, category, price from (SELECT *, (rank() over(partition by category"
            + " order by price asc, brand desc)) as rank FROM product) where rank = 1", nativeQuery = true)
    List<ProductEntity> loadLowestPriceProductsByCategory();


    @Query(value = "SELECT brand, SUM(min_price) AS totalPrice "
            + "FROM (SELECT brand, category, MIN(price) AS min_price FROM product GROUP BY brand, category) "
            + "AS min_prices GROUP BY brand ORDER BY totalPrice", nativeQuery = true)
    List<AllCategoryPriceSum> loadAllCategoryPriceSumByBrand();


    @Query(value = "SELECT *, (rank() over(partition by category order by price asc)) as rnk "
            + "FROM product where brand = :brand", nativeQuery = true)
    List<ProductEntity> loadLowestPriceProductsByBrand(@Param("brand") String brand);
}