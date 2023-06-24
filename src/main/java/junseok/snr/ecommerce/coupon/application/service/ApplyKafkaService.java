package junseok.snr.ecommerce.coupon.application.service;

import junseok.snr.ecommerce.coupon.domain.repository.CouponCountRepository;
import junseok.snr.ecommerce.coupon.infrastructure.producer.CouponCreateProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ApplyKafkaService {
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;

    @Transactional
    public void apply(Long userId) {
        final Long count = couponCountRepository.increment();

        if (count > 100) return;

        couponCreateProducer.create(userId);
    }
}
