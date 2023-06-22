package junseok.snr.ecommerce.domain;

import junseok.snr.ecommerce.order.domain.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    private static int threadCount = 0;

    @BeforeAll
    static void setUp() {
        threadCount = Runtime.getRuntime().availableProcessors() * 2;
    }

    @ParameterizedTest(name = "멀티쓰레드 요청 시 Processor의 2배 쓰레드에서 [{index}]재고 값이 {0}일 때, 한번에 5-9씩 {1}번 차감해도 재고는 0이하로 떨어지지 않는다.")
    @CsvSource({
            "45, 23",
            "89, 45",
            "43, 22",
            "89, 45",
            "79, 40",
            "26, 13",
            "81, 41",
            "85, 43",
            "99, 50",
            "60, 30",
            "35, 18",
            "64, 32",
            "7, 4",
            "8, 4",
            "41, 21",
            "50, 25",
            "70, 35",
            "7, 4",
            "5, 3"
    })
    void reduceStockMultiThreadTest(int stock, int limit) throws InterruptedException {
        final Product product = Product.builder()
                .stock(new AtomicInteger(stock))
                .build();
        final ExecutorService service = Executors.newFixedThreadPool(threadCount);

        final Random random = new Random();
        IntStream.generate(() -> random.nextInt(10) + 5)
                .limit(limit)
                .forEach(
                        i -> service.submit(() -> product.reduceStock(i))
                );

        assertThat(product.getStock().get()).isNotNegative();
    }

    @DisplayName("멀티쓰레드 요청 시 Processor의 2배 쓰레드에서 500번 테스트 해도 음수가 발생하면 안된다.")
    @RepeatedTest(500)
    void reduceStockMultiThreadTest() throws InterruptedException {
        final Product product = Product.builder()
                .stock(new AtomicInteger(50))
                .build();
        final ExecutorService service = Executors.newFixedThreadPool(threadCount);

        final Random random = new Random();
        IntStream.generate(() -> random.nextInt(10) + 5)
                .limit(50)
                .forEach(i -> service.submit(() -> product.reduceStock(i))
                );

        service.shutdown();
        service.awaitTermination(60, TimeUnit.SECONDS);

        assertThat(product.getStock().get()).isNotNegative();
    }


    @DisplayName("멀티쓰레드 요청 시 Processor의 2배 쓰레드에서 500번 반복적으로 테스트 한 경우 동일한 결과가 나와야 한다.")
    @RepeatedTest(500)
    void reduceStockTest() throws InterruptedException {
        final ExecutorService service = Executors.newFixedThreadPool(threadCount);
        final Product product = Product.builder()
                .stock(new AtomicInteger(15))
                .build();

        IntStream.generate(() -> 3)
                .limit(5)
                .forEach(i -> service.submit(() -> product.reduceStock(i)));

        service.shutdown();
        service.awaitTermination(60, TimeUnit.SECONDS);

        assertThat(product.getStock().get()).isZero();
    }

}