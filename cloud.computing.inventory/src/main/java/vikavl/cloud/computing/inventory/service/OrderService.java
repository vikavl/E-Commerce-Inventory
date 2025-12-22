package vikavl.cloud.computing.inventory.service;

import org.springframework.stereotype.Service;
import vikavl.cloud.computing.inventory.dto.CreateOrderRequest;
import vikavl.cloud.computing.inventory.dto.OrderDto;

@Service
public class OrderService {
    public OrderDto getOrder(String id) {
        return null;
    }

    public void placeOrder(CreateOrderRequest createOrderRequest) {
        /**
         * todo
         *  1. validate stock
         *  2. decrease stock
         *  3. create order + order items
         * */

    }
}
