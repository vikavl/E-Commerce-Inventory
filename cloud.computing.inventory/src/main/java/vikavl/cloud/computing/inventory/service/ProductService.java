package vikavl.cloud.computing.inventory.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vikavl.cloud.computing.inventory.dto.ProductDto;

import java.util.List;

@Service
public class ProductService {
    public List<ProductDto> getAllProducts(Pageable pageable) {
        return null;
    }

    public ProductDto getProduct(String id) {
        return null;
    }

    public void createProduct(ProductDto product) {
    }

    public void updateProduct(String id, ProductDto product) {
    }
}
