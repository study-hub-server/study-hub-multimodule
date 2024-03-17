package com.example.webclient.api.user.dto.request;

import com.example.core.user.dto.data.UpdatePasswordData;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@inu\\.ac\\.kr$")
    private String email;

    @Schema(description = "유저 비밀번호", example = "asd123")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*?~_]).{10,}$",
            message = "비밀번호는 10자 이상이어야 하며, 하나 이상의 특수문자를 포함해야 합니다."
    )
    @NotBlank(message = "비밀번호는 입력하셔야 합니다")
    private String password;

    @Schema(description = "해당 유저가 기존 비밀번호를 확인했는지")
    @NotNull(message = "접근권한이 없는 유저입니다")
    private boolean auth;

    @Builder
    public UpdatePasswordRequest(String email, String password, boolean auth) {
        this.email = email;
        this.password = password;
        this.auth = auth;
    }

    public UpdatePasswordData toService() {
        return UpdatePasswordData.builder()
                .password(password)
                .auth(auth)
                .build();
    }
}
