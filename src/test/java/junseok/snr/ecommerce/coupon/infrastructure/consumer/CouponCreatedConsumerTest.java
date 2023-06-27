package junseok.snr.ecommerce.coupon.infrastructure.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {
                "listener=PLAINTEXT://localhost:9092",
                "port=9092"
        }
)
class CouponCreatedConsumerTest {

    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplate;
    @Autowired
    private CouponCreatedConsumer consumer;

    @Test
    void testListener() throws InterruptedException {
        final long userId = 1L;
        kafkaTemplate.send("coupon_create", userId);

        Thread.sleep(1_000);

        assertThat(consumer.listener(userId)).isEqualTo(userId);
    }
}