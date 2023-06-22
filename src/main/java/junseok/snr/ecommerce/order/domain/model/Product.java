package junseok.snr.ecommerce.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;


@AllArgsConstructor
@Builder
@Getter
public class Product {
    private int productId;
    private String productName;
    private int price;
    private AtomicInteger stock;

    public void reduceStock(int quantity) {
        validateReduceStock(quantity);
        stock.addAndGet(-quantity);
    }

    private void validateReduceStock(int quantity) {
        if (stock.get() < quantity) throw new RuntimeException();
    }

}
