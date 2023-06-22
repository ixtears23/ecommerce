package junseok.snr.ecommerce.order.domain;

public interface DiscountPolicy {
    int apply(int orderAmount);
    boolean isDiscount(int orderAmount);
}
