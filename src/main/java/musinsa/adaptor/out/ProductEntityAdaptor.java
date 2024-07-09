package musinsa.adaptor.out;


import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import musinsa.application.port.out.ProductRepositoryPort;
import musinsa.domain.ProductDomain;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class ProductEntityAdaptor implements ProductRepositoryPort {

    private final ProductEntityRepository productEntityRepository;
    private final ProductEntityAdaptorMapper productEntityAdaptorMapper;


    @Override
    public List<ProductDomain> loadAllProductDomainList() {
        return productEntityRepository.findAll().stream().map(productEntityAdaptorMapper::toProductDomain).collect(
                Collectors.toList());
    }


    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
    interface ProductEntityAdaptorMapper {

        ProductDomain toProductDomain(ProductEntity productEntity);
    }
}
