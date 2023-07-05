package junseok.snr.ecommerce.coupon.infrastructure.config;

import jakarta.annotation.PreDestroy;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Collections;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    public static final String COUPON_CREATE = "coupon_create";
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(
                Map.of(
                        AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers
                )
        );
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic(COUPON_CREATE, 1, (short) 1);
    }

    @PreDestroy
    public void preDestroy() {
        deleteTopic();
    }

    private void deleteTopic() {
        try (AdminClient adminClient = AdminClient.create(kafkaAdmin()
                .getConfigurationProperties())) {
            adminClient.deleteTopics(Collections.singletonList(COUPON_CREATE));
        }
    }
}
