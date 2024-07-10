package musinsa.product.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import musinsa.product.adaptor.in.dto.EnrollProductDto;
import musinsa.product.adaptor.in.dto.UpdateProductDto;
import musinsa.product.application.port.in.DeleteProductUseCase;
import musinsa.product.application.port.in.EnrollProductUseCase;
import musinsa.product.application.port.in.GetProductUseCase;
import musinsa.product.application.port.in.UpdateProductUseCase;
import musinsa.product.application.port.in.dto.ProductDto;
import musinsa.product.application.port.out.ProductPersistencePort;
import musinsa.product.application.port.out.command.SaveProductCommand;
import musinsa.product.application.port.out.command.UpdateProductCommand;
import musinsa.product.domain.ProductDomain;
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
    public List<ProductDto> getProductList() {
        return productPersistencePort.loadAllProductDomainList().stream().map(productServiceMapper::toProductDto)
                .toList();
    }


    @Override
    public ProductDto enrollProduct(EnrollProductDto enrollProductDto) {

        ProductDomain productDomain =
                productPersistencePort.saveProduct(productServiceMapper.toSaveProductCommand(enrollProductDto));

        return productServiceMapper.toProductDto(productDomain);
    }


    @Override
    public ProductDto updateProduct(long id, UpdateProductDto updateProductDto) {

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

        ProductDto toProductDto(ProductDomain productDomain);

        SaveProductCommand toSaveProductCommand(EnrollProductDto enrollProductDto);

        UpdateProductCommand toUpdateProductCommand(UpdateProductDto updateProductDto);
    }
}
