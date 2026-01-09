package vikavl.cloud.computing.inventory.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vikavl.cloud.computing.inventory.model.Product;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("""
                    select p from Product p
                    order by rand()
                    limit :number
                    """)
    List<Product> findRandomProducts(@Param("number") int n);

    List<Product> findAllByUpdatedAtAfterOrderByUpdatedAtDesc(LocalDateTime since);
}
