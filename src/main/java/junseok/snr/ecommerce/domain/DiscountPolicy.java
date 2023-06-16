package junseok.snr.ecommerce.domain;

public interface DiscountPolicy {
    int apply(int orderAmount);
    boolean isDiscount(int orderAmount);
}
