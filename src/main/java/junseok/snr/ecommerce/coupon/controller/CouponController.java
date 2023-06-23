package junseok.snr.ecommerce.coupon.controller;

import junseok.snr.ecommerce.coupon.application.service.ApplyRedisService;
import junseok.snr.ecommerce.coupon.application.service.ApplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/coupon/v1")
@RequiredArgsConstructor
@RestController
public class CouponController {
    private final ApplyRedisService applyRedisService;
    private final ApplyService applyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/issue")
    void issueCoupon(final long userId) {
        log.info(">>>>> issueCoupon - userId : {}", userId);
        applyRedisService.apply(userId);
    }
}
