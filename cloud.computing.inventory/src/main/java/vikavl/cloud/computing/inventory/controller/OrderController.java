package vikavl.cloud.computing.inventory.controller;

import org.springframework.web.bind.annotation.*;
import vikavl.cloud.computing.inventory.dto.CreateOrderRequest;
import vikavl.cloud.computing.inventory.dto.OrderDto;
import vikavl.cloud.computing.inventory.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public OrderDto getProduct(@PathVariable String id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public void placeOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.placeOrder(createOrderRequest);
    }
}
