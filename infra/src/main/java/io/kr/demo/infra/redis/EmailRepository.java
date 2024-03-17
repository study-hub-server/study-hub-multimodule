package io.kr.demo.infra.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class EmailRepository {

    private final StringRedisTemplate redisTemplate;

    public void saveCertificationNumber(String email, String key) {
        redisTemplate.opsForValue().set(email, key, 1000L * 60 * 5, TimeUnit.MILLISECONDS);
    }

    public String validEmail(String email) {
        return redisTemplate.opsForValue().get(email);
    }
}

