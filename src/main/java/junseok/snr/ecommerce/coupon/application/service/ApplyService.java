package junseok.snr.ecommerce.coupon.application.service;

import junseok.snr.ecommerce.coupon.domain.model.Coupon;
import junseok.snr.ecommerce.coupon.domain.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
    private final CouponRepository couponRepository;

    public ApplyService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public void apply(Long userId) {
        final int count = couponRepository.hashCode();

        if (count > 100) return;

        couponRepository.save(new Coupon(userId));
    }
}
