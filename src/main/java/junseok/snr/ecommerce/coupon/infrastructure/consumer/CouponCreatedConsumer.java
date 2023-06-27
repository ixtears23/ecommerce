package junseok.snr.ecommerce.coupon.infrastructure.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CouponCreatedConsumer {

    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public Long listener(Long userId) {
        log.info(">>>>> topic : coupon_create, userId : {}", userId);
        return userId;
    }
}
