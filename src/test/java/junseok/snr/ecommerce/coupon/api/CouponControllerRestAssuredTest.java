package junseok.snr.ecommerce.coupon.api;


import junseok.snr.ecommerce.coupon.api.dto.IssueCouponRequest;
import junseok.snr.ecommerce.coupon.domain.repository.CouponCountRepository;
import junseok.snr.ecommerce.coupon.domain.repository.CouponRepository;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CouponControllerRestAssuredTest {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CouponCountRepository couponCountRepository;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        couponRepository.deleteAll();
        couponCountRepository.deleteAll();
    }

    @Test
    void testIssueCoupon() {
        final IssueCouponRequest request = new IssueCouponRequest(1L);

        given()
                .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                .port(port)
                .body(request)
        .when()
                .post("/coupon/v1/issue")
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

}