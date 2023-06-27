package junseok.snr.ecommerce.coupon.application.service;

import junseok.snr.ecommerce.coupon.domain.repository.CouponCountRepository;
import junseok.snr.ecommerce.coupon.domain.repository.CouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplyKafkaServiceTest {
    @Autowired
    private ApplyKafkaService applyKafkaService;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CouponCountRepository couponCountRepository;

    @BeforeEach
    void setUp() {
        couponRepository.deleteAll();
        couponCountRepository.deleteAll();
    }

    @DisplayName("쿠폰을 딱 한번 생성했을 때 쿠폰 조회 시 쿠폰 갯수는 1개여야 한다")
    @Test
    void apply_only_once_test() {
        applyKafkaService.apply(1L);

        final long count = couponRepository.count();

        assertThat(count).isEqualTo(1L);
    }

    @DisplayName("100장만 발급가능한 쿠폰이 100장 만큼만 발급되어야 한다")
    @Test
    void apply_sync_multiple_times_test() throws InterruptedException {
        final long count = countApply();

        assertThat(count).isEqualTo(100);
    }

    private long countApply() throws InterruptedException {
        int threadCount = 1_000;
        final ExecutorService executorService = Executors.newFixedThreadPool(32);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    applyKafkaService.apply(userId);
                } finally {
                    countDownLatch.countDown();
                }

            });
        }

        countDownLatch.await();

        Thread.sleep(10_000);

        return couponRepository.count();
    }

}