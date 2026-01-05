package vikavl.cloud.computing.inventory.mapper;

import org.mapstruct.Mapper;
import vikavl.cloud.computing.inventory.dto.ProductDto;
import vikavl.cloud.computing.inventory.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductDto, Product> {
    ProductDto toDto(Product entity);
    Product toEntity(ProductDto dto);
}
