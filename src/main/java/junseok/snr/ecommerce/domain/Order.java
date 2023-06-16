package junseok.snr.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class Order {
    private List<OrderItem> orderItemList = new ArrayList<>();
    private OrderStatus orderStatus;
    private int orderAmount;
    private int paymentAmount;
    private DiscountPolicy discountPolicy;

    public Order(DiscountPolicy discountPolicy) {
        this.orderStatus = OrderStatus.PROCESSING;
        this.discountPolicy = discountPolicy;
    }

    public void addOrderItem(OrderItem orderItem) {
        final Optional<OrderItem> existingOrderItemOptional = orderItemList.stream()
                .filter(item -> item.getProduct().getProductId() == orderItem.getProduct().getProductId())
                .findFirst();

        if (existingOrderItemOptional.isPresent()) {
            final OrderItem existingOrderItem = existingOrderItemOptional.get();
            existingOrderItem.increaseQuantity(orderItem.getQuantity());
            return;
        }

        orderItemList.add(orderItem);
    }

    public void completeOrder() {
        orderStatus = OrderStatus.COMPLETED;
        calculateOrderAmount();
        calculatePaymentAmount();
        orderItemList.forEach(orderItem ->
                        orderItem.getProduct()
                                .reduceStock(orderItem.getQuantity())
                );
    }

    private void calculateOrderAmount() {
        orderItemList.forEach(OrderItem::calculateOrderAmount);
        orderAmount = orderItemList.stream()
                .mapToInt(OrderItem::getOrderAmount)
                .sum();
    }

    private void calculatePaymentAmount() {
        paymentAmount = orderAmount;
        if (discountPolicy != null) paymentAmount = discountPolicy.apply(orderAmount);
    }

}
