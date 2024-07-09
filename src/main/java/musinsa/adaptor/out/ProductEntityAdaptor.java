package musinsa.adaptor.out;


import java.util.List;
import lombok.RequiredArgsConstructor;
import musinsa.application.port.out.ProductRepositoryPort;
import musinsa.application.port.out.command.SaveProductCommand;
import musinsa.domain.ProductDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
        return productEntityRepository.findAll().stream().map(productEntityAdaptorMapper::toProductDomain).toList();
    }


    @Override
    public void saveProductList(List<SaveProductCommand> saveProductCommandList) {

        List<ProductEntity> productEntityList =
                saveProductCommandList.stream().map(productEntityAdaptorMapper::toProductEntity).toList();

        productEntityRepository.saveAll(productEntityList);
    }


    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
    interface ProductEntityAdaptorMapper {

        ProductDomain toProductDomain(ProductEntity productEntity);

        @Mapping(target = "id", ignore = true)
        ProductEntity toProductEntity(SaveProductCommand saveProductCommand);
    }
}
