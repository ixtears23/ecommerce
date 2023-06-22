package junseok.snr.ecommerce.domain;

import junseok.snr.ecommerce.order.domain.model.Order;
import junseok.snr.ecommerce.order.domain.model.OrderItem;
import junseok.snr.ecommerce.order.domain.model.OrderStatus;
import junseok.snr.ecommerce.order.domain.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @DisplayName("주문 생성 시 주문상태는 PROCESSING 상태여야 한다.")
    @Test
    void initialOrderTest() {
        final Order order = new Order(null);

        final OrderStatus orderStatus = order.getOrderStatus();

        assertThat(orderStatus).isEqualByComparingTo(OrderStatus.PROCESSING);
    }

    @DisplayName("동일한 상품을 주문하는 경우, 주문 아이템 목록에 하나의 상품만 들어가고 수량만 더해져야 한다.")
    @Test
    void addSameOrderItemTest() {
        final Order order = new Order(null);
        final Product product = Product.builder()
                .productId(648418)
                .build();

        order.addOrderItem(new OrderItem(product, 5));
        order.addOrderItem(new OrderItem(product, 3));

        final OrderItem orderedItem = order.getOrderItemList()
                .get(0);

        assertThat(order.getOrderItemList()).hasSize(1);
        assertThat(orderedItem.getQuantity()).isEqualTo(8);
    }

}