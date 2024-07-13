package musinsa.product.application.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import musinsa.product.adaptor.in.dto.EnrollProductDto;
import musinsa.product.adaptor.in.dto.UpdateProductDto;
import musinsa.product.application.port.in.DeleteProductUseCase;
import musinsa.product.application.port.in.EnrollProductUseCase;
import musinsa.product.application.port.in.GetProductUseCase;
import musinsa.product.application.port.in.UpdateProductUseCase;
import musinsa.product.application.port.in.dto.AdminProductDto;
import musinsa.product.application.port.in.dto.LowestHighestPriceBrandDto;
import musinsa.product.application.port.in.dto.LowestPriceProductDto;
import musinsa.product.application.port.in.dto.LowestPriceSaleBrandDto;
import musinsa.product.application.port.out.ProductPersistencePort;
import musinsa.product.application.port.out.command.SaveProductCommand;
import musinsa.product.application.port.out.command.UpdateProductCommand;
import musinsa.product.application.port.out.dto.AllCategoryPriceSum;
import musinsa.product.domain.ProductDomain;
import musinsa.product.domain.enums.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ProductService implements GetProductUseCase, EnrollProductUseCase, UpdateProductUseCase, DeleteProductUseCase {

    private final ProductPersistencePort productPersistencePort;
    private final ProductServiceMapper productServiceMapper;


    @Override
    public List<AdminProductDto> getProductList() {
        return productPersistencePort.loadAllProductDomainList().stream().map(productServiceMapper::toProductDto)
                .toList();
    }


    @Override
    public LowestPriceProductDto getLowestPriceProductsByCategory() {

        List<ProductDomain> productList = productPersistencePort.loadLowestPriceProductsByCategory();

        AtomicLong totalPrice = new AtomicLong();

        List<LowestPriceProductDto.LowestPriceProduct> low = productList.stream().map(productDomain -> {
            totalPrice.addAndGet(productDomain.getPrice());

            return productServiceMapper.toLowestPriceProduct(productDomain);
        }).toList();

        return LowestPriceProductDto.builder().productList(low).totalPrice(totalPrice.get()).build();
    }


    @Override
    public LowestPriceSaleBrandDto getLowestPriceCategoryProductsByBrand() {

        List<AllCategoryPriceSum> allCategoryPriceSumList = productPersistencePort.loadAllCategoryPriceSumByBrand();

        AllCategoryPriceSum allCategoryPriceSum = allCategoryPriceSumList.get(0);

        List<ProductDomain> lowestPriceCategoryProducts =
                productPersistencePort.loadLowestPriceCategoryProductsByBrand(allCategoryPriceSum.getBrand());

        LowestPriceSaleBrandDto.LowestPriceSaleBrand lowestPriceSaleBrand =
                LowestPriceSaleBrandDto.LowestPriceSaleBrand.builder().brand(allCategoryPriceSum.getBrand())
                        .categories(productServiceMapper.toCategoryProduct(lowestPriceCategoryProducts))
                        .totalPrice(allCategoryPriceSum.getTotalPrice()).build();

        return LowestPriceSaleBrandDto.builder().lowestPriceSaleBrand(lowestPriceSaleBrand).build();
    }


    @Override
    public LowestHighestPriceBrandDto getLowestHighestPriceBrandByCategory(Category category) {

        List<ProductDomain> lowestPriceProducts = productPersistencePort.loadLowestPriceBrandByCategory(category);

        List<LowestHighestPriceBrandDto.BrandProduct> lowestPriceBrandList =
                lowestPriceProducts.stream().map(productServiceMapper::toBrandProduct).toList();

        List<ProductDomain> highestPriceProducts = productPersistencePort.loadHighestPriceBrandByCategory(category);

        List<LowestHighestPriceBrandDto.BrandProduct> highestPriceBrandList =
                highestPriceProducts.stream().map(productServiceMapper::toBrandProduct).toList();

        return LowestHighestPriceBrandDto.builder().category(category).lowestPrices(lowestPriceBrandList)
                .highestPrices(highestPriceBrandList).build();
    }


    @Override
    public AdminProductDto enrollProduct(EnrollProductDto enrollProductDto) {

        ProductDomain productDomain =
                productPersistencePort.saveProduct(productServiceMapper.toSaveProductCommand(enrollProductDto));

        return productServiceMapper.toProductDto(productDomain);
    }


    @Override
    public AdminProductDto updateProduct(long id, UpdateProductDto updateProductDto) {

        ProductDomain productDomain =
                productPersistencePort.updateProduct(id, productServiceMapper.toUpdateProductCommand(updateProductDto));

        return productServiceMapper.toProductDto(productDomain);
    }


    @Override
    public void deleteProduct(long id) {
        productPersistencePort.deleteProduct(id);
    }


    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
    interface ProductServiceMapper {

        AdminProductDto toProductDto(ProductDomain productDomain);

        SaveProductCommand toSaveProductCommand(EnrollProductDto enrollProductDto);

        UpdateProductCommand toUpdateProductCommand(UpdateProductDto updateProductDto);

        LowestPriceProductDto.LowestPriceProduct toLowestPriceProduct(ProductDomain productDomain);

        List<LowestPriceSaleBrandDto.LowestPriceSaleBrand.CategoryProduct> toCategoryProduct(
                List<ProductDomain> productDomainList);

        LowestHighestPriceBrandDto.BrandProduct toBrandProduct(ProductDomain productDomain);
    }
}
