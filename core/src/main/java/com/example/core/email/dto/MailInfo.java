package com.example.core.email.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MailInfo {

    private final String email;

    @Builder
    public MailInfo(String email) {
        this.email = email;
    }
}
