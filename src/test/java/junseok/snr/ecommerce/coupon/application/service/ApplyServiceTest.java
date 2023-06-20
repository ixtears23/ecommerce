package junseok.snr.ecommerce.coupon.application.service;

import junseok.snr.ecommerce.coupon.domain.repository.CouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplyServiceTest {

    @Autowired
    private ApplyService applyService;
    @Autowired
    private CouponRepository couponRepository;

    @DisplayName("쿠폰을 딱 한번 생성했을 때 쿠폰 조회 시 쿠폰 갯수는 1개여야 한다")
    @Test
    void apply_only_once_test() {
        applyService.apply(1L);

        final long count = couponRepository.count();

        assertThat(count).isEqualTo(1L);
    }
}