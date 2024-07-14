package musinsa.product.adaptor.out;


import java.util.List;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static musinsa.product.adaptor.out.QProductEntity.productEntity;
import musinsa.product.domain.enums.Category;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    public List<ProductEntity> loadLowestPriceBrandByCategory(Category category) {

        Long minPrice = jpaQueryFactory.select(productEntity.price.min()).from(productEntity)
                .where(productEntity.category.eq(category)).fetchOne();

        if(minPrice == null) {
            return List.of();
        }

        return jpaQueryFactory.selectFrom(productEntity)
                .where(productEntity.price.eq(minPrice).and(productEntity.category.eq(category))).fetch();
    }


    public List<ProductEntity> loadHighestPriceBrandByCategory(Category category) {
        Long minPrice = jpaQueryFactory.select(productEntity.price.max()).from(productEntity)
                .where(productEntity.category.eq(category)).fetchOne();

        if(minPrice == null) {
            return List.of();
        }

        return jpaQueryFactory.selectFrom(productEntity)
                .where(productEntity.price.eq(minPrice).and(productEntity.category.eq(category))).fetch();
    }


    public List<String> loadAllBrandIncludingAllCategories(int categoryCount) {

        return jpaQueryFactory.select(productEntity.brand).from(productEntity).groupBy(productEntity.brand)
                .having(productEntity.category.count().goe(categoryCount)).fetch();
    }
}
