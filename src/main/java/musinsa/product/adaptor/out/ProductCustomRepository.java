package musinsa.product.adaptor.out;


import java.util.List;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static musinsa.product.adaptor.out.QProductEntity.productEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    public List<ProductEntity> loadLowestPriceBrandByCategory(String category) {

        Long minPrice = jpaQueryFactory.select(productEntity.price.min()).from(productEntity)
                .where(productEntity.category.eq(category)).fetchOne();

        if(minPrice == null) {
            return List.of();
        }

        return jpaQueryFactory.selectFrom(productEntity)
                .where(productEntity.price.eq(minPrice).and(productEntity.category.eq(category))).fetch();
    }


    public List<ProductEntity> loadHighestPriceBrandByCategory(String category) {
        Long minPrice = jpaQueryFactory.select(productEntity.price.max()).from(productEntity)
                .where(productEntity.category.eq(category)).fetchOne();

        if(minPrice == null) {
            return List.of();
        }

        return jpaQueryFactory.selectFrom(productEntity)
                .where(productEntity.price.eq(minPrice).and(productEntity.category.eq(category))).fetch();
    }
}
