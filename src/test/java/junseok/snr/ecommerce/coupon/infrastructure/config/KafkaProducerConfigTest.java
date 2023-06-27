package junseok.snr.ecommerce.coupon.infrastructure.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KafkaProducerConfigTest {
    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplate;

    @Test
    void kafkaTemplateTest() {
        kafkaTemplate.send("coupon_create", 1L);
    }
}