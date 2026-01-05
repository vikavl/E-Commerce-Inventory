package vikavl.cloud.computing.inventory.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vikavl.cloud.computing.inventory.model.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("""
       select oi.product.id, sum(oi.quantity)
       from OrderItem oi
       where oi.order.createdAt >= :since
       group by oi.product.id
    """)
    List<Object[]> sumSoldByProductSince(@Param("since") LocalDateTime since);
}
