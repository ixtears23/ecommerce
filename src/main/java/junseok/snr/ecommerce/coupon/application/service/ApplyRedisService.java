package junseok.snr.ecommerce.coupon.application.service;

import junseok.snr.ecommerce.coupon.domain.model.Coupon;
import junseok.snr.ecommerce.coupon.domain.repository.CouponCountRepository;
import junseok.snr.ecommerce.coupon.domain.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplyRedisService {
    private final Logger log = LoggerFactory.getLogger(ApplyRedisService.class);
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    public ApplyRedisService(CouponRepository couponRepository, CouponCountRepository couponCountRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
    }

    @Transactional
    public void apply(Long userId) {
        final long count = couponCountRepository.increment();
        log.info("redis coupon count : {}", count);

        if (count > 100) return;

        log.info("saved coupon count : {}", count);
        couponRepository.save(new Coupon(userId));
    }

}
