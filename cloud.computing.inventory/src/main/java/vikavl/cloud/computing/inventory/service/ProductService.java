package vikavl.cloud.computing.inventory.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vikavl.cloud.computing.inventory.dto.InventoryChangeDto;
import vikavl.cloud.computing.inventory.dto.ProductDto;
import vikavl.cloud.computing.inventory.mapper.ProductMapper;
import vikavl.cloud.computing.inventory.model.Product;
import vikavl.cloud.computing.inventory.repository.OrderItemRepository;
import vikavl.cloud.computing.inventory.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, OrderItemRepository orderItemRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).get().map(productMapper::toDto).toList();
    }

    public ProductDto getProduct(String id) {
        return productRepository.findById(id).map(productMapper::toDto).orElse(null);
    }

    public void createProduct(ProductDto product) {
        Product product1 = productMapper.toEntity(product);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product1);
    }

    public void updateProduct(String id, ProductDto product) {
        productRepository.findById(id).ifPresent(
                product1 -> {
                    product1 = productMapper.toEntity(product);
                    product1.setUpdatedAt(LocalDateTime.now());
                    productRepository.save(product1);
                }
        );
    }

    public List<InventoryChangeDto> getChangesLastHours(int hours) {
        LocalDateTime since = LocalDateTime.now().minusHours(hours);

        // quantity of products sold in n hours
        // <"id", "quantity">
        Map<String, Integer> sold = new HashMap<>();
        // unpack query response
        for (Object[] row : orderItemRepository.sumSoldByProductSince(since)) {
            String productId = (String) row[0];
            Long sumQuantity = (Long) row[1];
            sold.put(productId, sumQuantity.intValue());
        }

        // check current state of stock of sold items in last n hours
        List<InventoryChangeDto> result = new ArrayList<>();
        for (Map.Entry<String, Integer> e : sold.entrySet()) {
            Product p = productRepository.findById(e.getKey())
                    .orElseThrow();
            result.add(new InventoryChangeDto(p.getId(), e.getValue(), p.getStockQuantity()));
        }
        return result;
    }
}
