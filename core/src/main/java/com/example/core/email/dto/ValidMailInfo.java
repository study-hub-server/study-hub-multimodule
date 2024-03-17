package com.example.core.email.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ValidMailInfo {

    private final String email;
    private final String authCode;

    @Builder
    public ValidMailInfo(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
    }
}