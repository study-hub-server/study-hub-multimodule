package io.kr.demo.infra.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.kr.demo.infra.jwt.dto.JwtDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Transactional
@Service
public class JwtProvider {

    private final RedisTemplate<Long, String> redisTemplate;

    @Value("Nana")
    private String SECRET;

    public JwtDto createJwtResponseDto(Long id) {
        return JwtDto.builder()
                .accessToken(createAccessToken(id))
                .refreshToken(createRefreshToken(id))
                .build();
    }

    private String createAccessToken(Long id) {
        String jwtToken = JWT.create()
                .withSubject("accessToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 60))
                .withClaim("id", id)
                .sign(Algorithm.HMAC512(SECRET));
        return "Bearer " + jwtToken;
    }

    private String createRefreshToken(Long id) {
        String jwtToken = JWT.create()
                .withSubject("refreshToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7 )) // 1주일
                .withClaim("id", id)
                .sign(Algorithm.HMAC512(SECRET));
        redisTemplate.opsForValue().set(id, jwtToken, 1000L * 60 * 60 * 24 * 7 * 4, TimeUnit.MILLISECONDS);
        return "Bearer " + jwtToken;
    }

    public String reissuedAccessToken(JwtDto jwtDto) {
        String refreshToken = jwtDto.getRefreshToken().replace("Bearer ", "");
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(refreshToken);
        Long id = decodedJWT.getClaim("id").asLong();

        if(refreshToken.equals(redisTemplate.opsForValue().get(id))) {
            return createAccessToken(id);
        }
        throw new RuntimeException("토큰이 없습니다!");
    }

    public String reissuedRefreshToken(JwtDto jwtDto) {
        String refreshToken = jwtDto.getRefreshToken().replace("Bearer ", "");
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(refreshToken);
        Long id = decodedJWT.getClaim("id").asLong();

        if(refreshToken.equals(redisTemplate.opsForValue().get(id))) {
            String token = createRefreshToken(id).replace("Bearer ", "");
            redisTemplate.delete(id);
            redisTemplate.opsForValue().set(id, token, 1000L * 60 * 60 * 24 * 7 * 4, TimeUnit.MILLISECONDS);
            return token;
        }
        throw new RuntimeException("토큰이 없습니다!");
    }
}
