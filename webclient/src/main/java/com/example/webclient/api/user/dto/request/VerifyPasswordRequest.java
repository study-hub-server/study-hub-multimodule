package com.example.webclient.api.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerifyPasswordRequest {

    private String password;

    @Builder
    public VerifyPasswordRequest(String password) {
        this.password = password;
    }
}
