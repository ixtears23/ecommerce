package junseok.snr.ecommerce.order.infrastructure.entity;

import jakarta.persistence.*;
import junseok.snr.ecommerce.order.domain.model.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> orderItemList = new ArrayList<>();
    private OrderStatus orderStatus;
    private int orderAmount;
    private int paymentAmount;

}
