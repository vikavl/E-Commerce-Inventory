package vikavl.cloud.computing.inventory.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        orderService.placeOrder(createOrderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
