package junseok.snr.ecommerce.coupon.api;

import junseok.snr.ecommerce.coupon.api.dto.IssueCouponRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CouponControllerSpringBootTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testIssueCoupon() {
        final IssueCouponRequest request = new IssueCouponRequest(1L);
        final ResponseEntity<Void> responseEntity = this.testRestTemplate
                .postForEntity("/coupon/v1/issue", request, Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
