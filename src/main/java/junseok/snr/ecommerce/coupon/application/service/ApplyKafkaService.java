package junseok.snr.ecommerce.coupon.application.service;

import junseok.snr.ecommerce.coupon.domain.repository.CouponCountRepository;
import junseok.snr.ecommerce.coupon.infrastructure.producer.CouponCreateProducer;
import junseok.snr.ecommerce.coupon.infrastructure.repository.redis.AppliedUserRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ApplyKafkaService {
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;
    private final AppliedUserRedisRepository appliedUserRedisRepository;

    @Transactional
    public void apply(Long userId) {
        final Long apply = appliedUserRedisRepository.add(userId);
        if (apply != 1) {
            return;
        }

        final Long count = couponCountRepository.increment();

        if (count > 100) return;

        couponCreateProducer.create(userId);
    }
}
