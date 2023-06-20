package junseok.snr.ecommerce.coupon.domain.repository;

import junseok.snr.ecommerce.coupon.domain.model.Coupon;

public interface CouponRepository {
    Coupon save(Coupon coupon);
    long count();
}
