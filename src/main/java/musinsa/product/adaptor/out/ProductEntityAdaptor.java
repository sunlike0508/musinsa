package musinsa.product.adaptor.out;


import java.util.List;
import lombok.RequiredArgsConstructor;
import musinsa.product.application.port.out.ProductPersistencePort;
import musinsa.product.application.port.out.command.SaveProductCommand;
import musinsa.product.domain.ProductDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class ProductEntityAdaptor implements ProductPersistencePort {

    private final ProductEntityRepository productEntityRepository;
    private final ProductEntityAdaptorMapper productEntityAdaptorMapper;


    @Override
    public List<ProductDomain> loadAllProductDomainList() {
        return productEntityRepository.findAll().stream().map(productEntityAdaptorMapper::toProductDomain).toList();
    }


    @Override
    public ProductDomain saveProduct(SaveProductCommand saveProductCommand) {
        return productEntityAdaptorMapper.toProductDomain(
                productEntityRepository.save(productEntityAdaptorMapper.toProductEntity(saveProductCommand)));
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
