package junseok.snr.ecommerce.coupon.domain.repository;

public interface CouponCountRepository {
    Long increment();
    void deleteAll();
}
