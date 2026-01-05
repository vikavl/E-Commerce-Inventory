package vikavl.cloud.computing.inventory.service;

import org.springframework.stereotype.Service;
import vikavl.cloud.computing.inventory.dto.CreateOrderRequest;
import vikavl.cloud.computing.inventory.dto.OrderDto;
import vikavl.cloud.computing.inventory.dto.OrderItemRequest;
import vikavl.cloud.computing.inventory.mapper.OrderMapper;
import vikavl.cloud.computing.inventory.model.Order;
import vikavl.cloud.computing.inventory.model.OrderItem;
import vikavl.cloud.computing.inventory.model.OrderStatus;
import vikavl.cloud.computing.inventory.model.Product;
import vikavl.cloud.computing.inventory.repository.OrderItemRepository;
import vikavl.cloud.computing.inventory.repository.OrderRepository;
import vikavl.cloud.computing.inventory.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDto getOrder(Long id) {
        return orderRepository.findById(id).map(orderMapper::toDto).orElse(null);
    }

    public void placeOrder(CreateOrderRequest request) {

        // 1. Load products and validate stock
        Map<String, Product> productsById = new HashMap<>();

        for (OrderItemRequest item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Product not found: " + item.getProductId()));

            if (product.getStockQuantity() < item.getQuantity()) {
                throw new IllegalStateException(
                        "Insufficient stock for product " + product.getId());
            }

            productsById.put(product.getId(), product);
        }

        // 2. Decrease stock
        for (OrderItemRequest item : request.getItems()) {
            Product product = productsById.get(item.getProductId());
            product.setStockQuantity(
                    product.getStockQuantity() - item.getQuantity());
        }

        // 3. Create order
        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        BigDecimal totalAmount = BigDecimal.ZERO;

        order = orderRepository.save(order);

        // 4. Create order items
        for (OrderItemRequest item : request.getItems()) {
            Product product = productsById.get(item.getProductId());

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(product.getPrice());

            BigDecimal itemTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);

            orderItemRepository.save(orderItem);
        }

        // 5. Update total amount
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
    }
}
