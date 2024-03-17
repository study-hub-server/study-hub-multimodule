package com.example.webclient.api.user.dto.response;

import com.example.core.user.dto.data.JwtData;
import lombok.Getter;

@Getter
public class JwtResponseDto {

    private final String accessToken;
    private final String refreshToken;

    public JwtResponseDto(JwtData jwtData) {
        this.accessToken = jwtData.getAccessToken();
        this.refreshToken = jwtData.getRefreshToken();
    }
}
