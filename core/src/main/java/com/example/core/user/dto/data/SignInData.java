package com.example.core.user.dto.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInData {

    private String email;

    private String password;

    @Builder
    public SignInData(String email, String password){
        this.email = email;
        this.password = password;
    }
}
