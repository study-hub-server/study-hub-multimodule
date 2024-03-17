package com.example.core.user.dto.data;

import io.kr.demo.infra.jwt.dto.JwtDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtData {

    private String accessToken;
    private String refreshToken;

    @Builder
    public JwtData(JwtDto jwtDto) {
        this.accessToken = jwtDto.getAccessToken();
        this.refreshToken = jwtDto.getRefreshToken();
    }
}
