package vikavl.cloud.computing.inventory.restocking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vikavl.cloud.computing.inventory.model.Product;
import vikavl.cloud.computing.inventory.service.ProductService;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RestockingTask {

    private Logger logger = LoggerFactory.getLogger(RestockingTask.class);

    private final ProductService service;

    @Value("${inventory.restock.products-number}")
    private int productsNumber;
    @Value("${inventory.restock.enabled}")
    private boolean enableRestock;

    public RestockingTask(ProductService service) {
        this.service = service;
    }

    // default 5 min
    @Scheduled(fixedRateString = "${inventory.restock.rate-ms}")
    public void restock() {
        if (enableRestock) {
            // retrieve
            List<Product> products = service.getRandomProducts(productsNumber);
            for (Product product : products) {
                // +5..+20
                int add = 5 + (int)(Math.random() * 16);
                product.setStockQuantity(product.getStockQuantity() + add);
                product.setUpdatedAt(LocalDateTime.now());
                // persist
                service.updateProduct(product);
            }
            logger.info("[{}] Restocking products number: {}", LocalDateTime.now(), productsNumber);
        }
    }
}
