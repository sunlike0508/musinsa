package musinsa.product.adaptor.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {}