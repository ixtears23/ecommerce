package junseok.snr.ecommerce.coupon.infrastructure.consumer;

import junseok.snr.ecommerce.coupon.domain.model.Coupon;
import junseok.snr.ecommerce.coupon.domain.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CouponCreatedConsumer {
    private final CouponRepository couponRepository;

    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public Long listener(Long userId) {
        log.info(">>>>> topic : coupon_create, userId : {}", userId);
        couponRepository.save(new Coupon(userId));
        return userId;
    }
}
