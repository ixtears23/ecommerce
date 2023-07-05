package junseok.snr.ecommerce.coupon.infrastructure.repository.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AppliedUserRedisRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public Long add(Long userId) {
        return redisTemplate.opsForSet()
                .add("applied_user", userId.toString());
    }

    public void deleteAll(final String key) {
        redisTemplate.delete("applied_user");
    }

}
