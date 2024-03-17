package io.kr.demo.infra.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtDto {

    private String accessToken;
    private String refreshToken;

    @Builder
    public JwtDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
