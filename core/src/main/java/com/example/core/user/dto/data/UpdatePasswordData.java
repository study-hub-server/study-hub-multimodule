package com.example.core.user.dto.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdatePasswordData {

    private String email;
    private final String password;
    private boolean auth;

    @Builder
    public UpdatePasswordData(String email, String password, boolean auth) {
        this.email = email;
        this.password = password;
        this.auth = auth;
    }
}
