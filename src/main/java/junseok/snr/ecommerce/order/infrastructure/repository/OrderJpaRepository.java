package junseok.snr.ecommerce.order.infrastructure.repository;

import junseok.snr.ecommerce.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
