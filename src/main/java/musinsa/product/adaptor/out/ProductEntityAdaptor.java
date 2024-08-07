package musinsa.product.adaptor.out;


import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import musinsa.product.application.port.out.LoadProductPort;
import musinsa.product.application.port.out.ProductPersistencePort;
import musinsa.product.application.port.out.command.SaveProductCommand;
import musinsa.product.application.port.out.command.UpdateProductCommand;
import musinsa.product.application.port.out.dto.AllCategoryPriceSum;
import musinsa.product.domain.ProductDomain;
import musinsa.product.domain.enums.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Component
class ProductEntityAdaptor implements ProductPersistencePort, LoadProductPort {

    private final ProductEntityRepository productEntityRepository;
    private final ProductCustomRepository productCustomRepository;
    private final ProductEntityAdaptorMapper productEntityAdaptorMapper;


    @Override
    @Transactional(readOnly = true)
    public List<ProductDomain> loadAllProductDomainList() {
        return productEntityRepository.findAll().stream().map(productEntityAdaptorMapper::toProductDomain).toList();
    }


    @Override
    @Transactional
    public ProductDomain saveProduct(SaveProductCommand saveProductCommand) {
        return productEntityAdaptorMapper.toProductDomain(
                productEntityRepository.save(productEntityAdaptorMapper.toProductEntity(saveProductCommand)));
    }


    @Override
    @Transactional
    public void saveProductList(List<SaveProductCommand> saveProductCommandList) {

        List<ProductEntity> productEntityList =
                saveProductCommandList.stream().map(productEntityAdaptorMapper::toProductEntity).toList();

        productEntityRepository.saveAll(productEntityList);
    }


    @Override
    @Transactional
    public ProductDomain updateProduct(long id, UpdateProductCommand updateProductCommand) {

        ProductEntity productEntity =
                productEntityRepository.findById(id).orElseThrow(() -> new NoSuchElementException("등록된 상품이 아닙니다."));

        if(StringUtils.hasText(updateProductCommand.getBrand())) {
            productEntity.setBrand(updateProductCommand.getBrand());
        }

        if(!ObjectUtils.isEmpty(updateProductCommand.getCategory())) {
            productEntity.setCategory(updateProductCommand.getCategory());
        }

        if(updateProductCommand.getPrice() > 0) {
            productEntity.setPrice(updateProductCommand.getPrice());
        }

        return productEntityAdaptorMapper.toProductDomain(productEntity);
    }


    @Override
    public void deleteProduct(long id) {
        ProductEntity productEntity =
                productEntityRepository.findById(id).orElseThrow(() -> new NoSuchElementException("등록된 상품이 아닙니다."));

        productEntityRepository.delete(productEntity);
    }


    @Override
    public List<ProductDomain> loadLowestPriceProductsByCategory() {
        List<ProductEntity> productEntityList = productEntityRepository.loadLowestPriceProductsByCategory();

        return productEntityList.stream().map(productEntityAdaptorMapper::toProductDomain).toList();
    }


    @Override
    public List<AllCategoryPriceSum> loadAllCategoryPriceSumByBrand(List<String> brandList) {
        return productEntityRepository.loadAllCategoryPriceSumByBrand(brandList);
    }


    @Override
    public List<ProductDomain> loadLowestPriceCategoryProductsByBrand(String brand) {

        List<ProductEntity> productEntityList = productEntityRepository.loadLowestPriceCategoryProductsByBrand(brand);

        return productEntityList.stream().map(productEntityAdaptorMapper::toProductDomain).toList();
    }


    @Override
    public List<ProductDomain> loadLowestPriceBrandByCategory(Category category) {
        List<ProductEntity> productEntityList = productCustomRepository.loadLowestPriceBrandByCategory(category);

        return productEntityList.stream().map(productEntityAdaptorMapper::toProductDomain).toList();
    }


    @Override
    public List<ProductDomain> loadHighestPriceBrandByCategory(Category category) {
        List<ProductEntity> productEntityList = productCustomRepository.loadHighestPriceBrandByCategory(category);

        return productEntityList.stream().map(productEntityAdaptorMapper::toProductDomain).toList();
    }


    @Override
    public List<String> loadAllBrandIncludingAllCategories(int categoryCount) {
        return productCustomRepository.loadAllBrandIncludingAllCategories(categoryCount);
    }


    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
    interface ProductEntityAdaptorMapper {

        ProductDomain toProductDomain(ProductEntity productEntity);

        @Mapping(target = "id", ignore = true)
        ProductEntity toProductEntity(SaveProductCommand saveProductCommand);
    }
}
