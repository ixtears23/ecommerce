package junseok.snr.ecommerce.order.infrastructure.repository;

import junseok.snr.ecommerce.order.domain.repository.OrderRepository;
import junseok.snr.ecommerce.order.infrastructure.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long>, OrderRepository {
}
