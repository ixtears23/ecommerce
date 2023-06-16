package junseok.snr.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderItem {
    private Product product;
    private int quantity;
    private int orderAmount;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void calculateOrderAmount() {
        orderAmount = product.getPrice() * quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
}
