package com.example.webclient.api.user.dto.request;

import com.example.core.user.dto.data.SignInData;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequest {

    @Schema(description = "유저 이메일", example = "studyHub@inu.ac.kr")
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@inu\\.ac\\.kr$")
    private String email;

    @Schema(description = "유저 비밀번호", example = "asdasdasd!!")
    @NotBlank
    private String password;

    @Builder
    public SignInRequest(String email, String password){
        this.email = email;
        this.password = password;
    }

    public SignInData toSignInData() {
        return SignInData.builder()
                .email(email)
                .password(password)
                .build();
    }
}