package junseok.snr.ecommerce.coupon.application.service;

import junseok.snr.ecommerce.coupon.domain.model.Coupon;
import junseok.snr.ecommerce.coupon.domain.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ApplyService {
    private final CouponRepository couponRepository;

    @Transactional
    public synchronized void applySync(Long userId) {
        apply(userId);
    }

    @Transactional
    public void applyNoneSync(Long userId) {
        apply(userId);
    }


    private void apply(Long userId) {
        final long count = couponRepository.count();

        if (count >= 100) return;

        couponRepository.save(new Coupon(userId));
    }

}
