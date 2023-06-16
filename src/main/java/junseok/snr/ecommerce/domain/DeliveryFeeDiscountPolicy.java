package junseok.snr.ecommerce.domain;

import org.springframework.stereotype.Component;

@Component
public class DeliveryFeeDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT_STANDARD_AMOUNT = 50_000;

    @Override
    public int apply(int orderAmount) {
        if (isDiscount(orderAmount)) return orderAmount;
        return orderAmount + DeliveryFee.AMOUNT;
    }

    @Override
    public boolean isDiscount(int orderAmount) {
        return orderAmount >= DISCOUNT_STANDARD_AMOUNT;
    }

}
