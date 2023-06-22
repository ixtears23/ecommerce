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
class ApplyRedisServiceTest {
    private final ApplyRedisService applyRedisService;
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    @Autowired
    public ApplyRedisServiceTest(ApplyRedisService applyRedisService, CouponRepository couponRepository, CouponCountRepository couponCountRepository) {
        this.applyRedisService = applyRedisService;
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
    }

    @BeforeEach
    void setUp() {
        couponRepository.deleteAll();
        couponCountRepository.deleteAll();
    }

    @DisplayName("100장만 발급가능한 쿠폰이 100장 만큼만 발급되어야 한다")
    @Test
    void apply_sync_multiple_times_test() throws InterruptedException {
        validateCouponCount();

        final int threadCount = 1_000;

        final ExecutorService executorService = Executors.newFixedThreadPool(32);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    applyRedisService.apply(userId);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();

        final long count = couponRepository.count();
        assertThat(count).isEqualTo(100);
    }

    private void validateCouponCount() {
        assertThat(couponRepository.count()).isZero();
    }

}