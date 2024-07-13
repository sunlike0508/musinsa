# musinsa

무신사 상품 관리 및 브랜드, 카테고리 별 코디 서비스

---

## API 상세 설명

* 호스트 : http://localhost:8080

#### 구현 1 : 카테고리 별로 최저가격인 브랜드와 가격 및 총액 조회

```
GET products/categories/lowest-price-products
```

#### 구현 2 : 단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액 조회

```
GET products/brands/lowest-price-brand
```

#### 구현 3 : 특정 카테고리에서 최저가격, 최고가격 브랜드 상품들의 가격 조회

```
GET products/lowest-highest-price-brand
```

#### 구현 4 : 상품 추가, 변경, 삭제

* 전체 조회

``` 
GET products
```

* 추가

``` 
POST products
```

* 변경

``` 
PATCH products/{id}
```

* 변경

``` 
DELETE products/{id}
```

---

### 개발 환경, 코드 빌드, 테스트, 실행 방법

* 개발 환경
    * IntelliJ
    * spring boot 3.3.1
    * 자바 17
    * Junit 5
    * H2 Database


* 테스트 DB
    * SpringBootApplication 실행되면 과제에서 내어준 데이터 내용으로 구성된 src/main/resources/product.json 파일을
      InitDataProcess.class가 읽고 h2(in-memory)에 자동으로 데이터가 추가 됩니다.

* 실행 방법
    * src/test/java/musinsa/product/adaptor/in에 있는
      AdminProductController.http
      ProductController.http 을 통해 POSTMAN처럼 API 호출 가능합니다.

---

### 기타추가정보

