package junseok.snr.ecommerce.coupon.infrastructure.repository.redis;

import junseok.snr.ecommerce.coupon.domain.repository.CouponCountRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CouponCountRedisRepositoryImpl implements CouponCountRepository {

    public static final String KEY = "coupon_count";
    private final RedisTemplate<String, String> redisTemplate;

    public CouponCountRedisRepositoryImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Long increment() {
        return redisTemplate.opsForValue()
                .increment(KEY);
    }

    @Override
    public void deleteAll() {
        redisTemplate.delete(KEY);
    }
}
