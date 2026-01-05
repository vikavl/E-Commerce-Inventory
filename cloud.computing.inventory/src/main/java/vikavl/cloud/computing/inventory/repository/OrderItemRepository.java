package vikavl.cloud.computing.inventory.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import vikavl.cloud.computing.inventory.model.OrderItem;

@Transactional
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
