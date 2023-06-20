package junseok.snr.ecommerce.coupon.infrastructure.repository;

import junseok.snr.ecommerce.coupon.domain.repository.CouponRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponJpaRepository extends JpaRepository<CouponJpaRepository, Long>, CouponRepository {
}
