package junseok.snr.ecommerce.coupon.api;

import junseok.snr.ecommerce.coupon.api.dto.IssueCouponRequest;
import junseok.snr.ecommerce.coupon.application.service.ApplyRedisService;
import junseok.snr.ecommerce.coupon.application.service.ApplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/coupon/v1")
@RequiredArgsConstructor
@RestController
public class CouponController {
    private final ApplyRedisService applyRedisService;
    private final ApplyService applyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/issue")
    public void issueCoupon(@RequestBody IssueCouponRequest request) {
        log.info(">>>>> issueCoupon - userId : {}", request.id());
        applyRedisService.apply(request.id());
    }
}
