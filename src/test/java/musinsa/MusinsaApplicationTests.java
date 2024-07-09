package musinsa;

import java.util.List;
import musinsa.application.port.out.ProductRepositoryPort;
import musinsa.domain.ProductDomain;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MusinsaApplicationTests {

	@Autowired
	private ProductRepositoryPort productRepositoryPort;


	@Test
	@DisplayName("초기화 데이터 72개 등록 확인")
	void contextLoads() {

		List<ProductDomain> productDomainList = productRepositoryPort.loadAllProductDomainList();

		Assertions.assertThat(productDomainList).hasSize(72);
	}

}
