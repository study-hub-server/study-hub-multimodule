package com.example.webclient.api.email.dto;

import com.example.core.email.dto.MailInfo;
import com.example.webclient.api.email.validator.NormalEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MailSendRequest {

    @Schema(description = "이메일 주소", example = "kdw990202@inu.ac.kr")
    @NotBlank
    @NormalEmail
    private String email;

    public MailInfo toMailInfo() {
        return MailInfo.builder()
                .email(email)
                .build();
    }

}
