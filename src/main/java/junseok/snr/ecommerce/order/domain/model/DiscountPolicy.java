package junseok.snr.ecommerce.order.domain.model;

public interface DiscountPolicy {
    int apply(int orderAmount);
    boolean isDiscount(int orderAmount);
}
