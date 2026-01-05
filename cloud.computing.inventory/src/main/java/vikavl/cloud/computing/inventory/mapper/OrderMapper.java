package vikavl.cloud.computing.inventory.mapper;

import org.mapstruct.Mapper;
import vikavl.cloud.computing.inventory.dto.OrderDto;
import vikavl.cloud.computing.inventory.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper extends BaseMapper<OrderDto, Order> {
}
