# musinsa

무신사 상품 관리 및 브랜드, 카테고리 별 코디 서비스

---

## API 설명 및 구현 범위

* 호스트 : http://localhost:8080

### 구현 1 : 카테고리 별로 최저 가격인 브랜드와 가격 및 총액 조회

```
GET products/categories/lowest-price-products
```

한 카테고리에 상품 최저 가격 상푸 한 개만 보여줘야 합니다.

그러나 같은 카테고리 내에 최저 가격이 같은 경우가 문제가 발생할 수 있습니다.

요구사항에는 없었으나 임의로 브랜드명 내림차순 기준 1개만 보여주도록 구현했습니다.

### 구현 2 : 단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액 조회

```
GET products/brands/lowest-price-categories
```

세번의 쿼리 조회로 나누어 구현했습니다.

1. 모든 카테고리가 등록된 브랜드를 조회
2. 1번에서 찾은 브랜드 별 최저가격 카테고리 상품 총액 계산 후 총액으로 오름차순하는 쿼리
3. 2번 결과에서 첫번째 row 브랜드의 카테고리 상품 조회 후 각 각테고리의 최저 가격 상품을 조회

### 구현 3 : 특정 카테고리에서 최저가격, 최고가격 브랜드 상품들의 가격 조회

```
GET products/lowest-highest-price-brand
```

여기서는 최저, 최고 금액을 구하는 query를 native query가 아닌 querydsl로 구현했습니다.
querydsl로 만든 이유는 역시나 자바로 쿼리 작성하기 때문에 컴파일 단게에서 오류를 발견 할 수 있고 유지, 보수가 쉽기 때문입니다.

구현1, 구현2에서 querydsl을 쓰지 않은 이유는 아직 rank 함수, from 절에 subquery를 지원하지 않지 않기 때문에 native query를 사용했습니다.

### 구현 4 : 상품 추가, 변경, 삭제

* 전체 조회

    ``` 
    GET products
    ```

* 추가

    ``` 
    POST products
    ```
  상품 등록을 위한 API, 브랜드, 카테고리, 가격을 모두 입력해야 등록 가능. 카테고리의 경우 Enum 값에 포함된 값만 가능. (Spring Restdocs 공통 Enum 문서 참조)

* 변경

    ``` 
    PATCH products/{id}
    ```
  브랜드, 카테고리, 가격 일부 데이터 수정 가능. 입력이 들어온 RequestBody에 들어온 데이터만 수정되기 때문에 PATCH를 사용

* 삭제

    ``` 
    DELETE products/{id}
    ```

---

## 개발 환경, DataBase, API 테스트 실행 방법

* 개발 환경
    * IntelliJ
    * spring boot 3.3.1
    * 자바 17
    * Junit 5
    * H2 Database


* (테스트) DB
    * SpringBootApplication 실행되면 과제에서 내어준 데이터 내용으로 구성된 src/main/resources/product.json 파일을
      InitDataProcess.class가 읽고 h2(in-memory)에 자동으로 데이터가 추가 됩니다.


* API 호출 HTTP 파일 위치
    * src/test/java/musinsa/product/adaptor/in/http에 있는
      AdminProductController.http
      ProductController.http 을 통해 POSTMAN처럼 API 호출 가능합니다.

---

## 기타추가정보

* Spring Rest Docs 적용

1. git 명령어 테스트 코드실행, 혹은 IDE(IntelliJ) 테스트실행

   ``` 
   ./gradlew :test
   ```

   build/generated-snippets 하위 폴더에 adoc 파일이 생성되면 정상


2. git 명령어 build 실행, 혹은 IDE 빌드 실행

   ``` 
   ./gradlew build
   ```

   src/main/resources/static/docs 폴더에 html 파일 생성되면 정상


3. Appplication 실행 후 http://localhost:8080/docs/index.html 접속 후 확인