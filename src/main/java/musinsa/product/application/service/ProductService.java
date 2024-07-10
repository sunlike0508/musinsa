package musinsa.product.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import musinsa.product.application.port.in.GetProductUseCase;
import musinsa.product.application.port.in.dto.ProductDto;
import musinsa.product.application.port.out.ProductRepositoryPort;
import musinsa.product.domain.ProductDomain;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ProductService implements GetProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ProductServiceMapper productServiceMapper;


    @Override
    public List<ProductDto> getProductList() {
        return productRepositoryPort.loadAllProductDomainList().stream().map(productServiceMapper::toProductDto)
                .toList();
    }


    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
    interface ProductServiceMapper {

        ProductDto toProductDto(ProductDomain productDomain);
    }
}
