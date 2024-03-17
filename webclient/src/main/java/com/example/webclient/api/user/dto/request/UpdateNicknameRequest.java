package com.example.webclient.api.user.dto.request;

import com.example.core.user.dto.data.UpdateNicknameData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateNicknameRequest {

    @NotBlank(message = "닉네임은 있어야 합니다")
    @Size(max = 10, message = "닉네임은 10자 이하여야 합니다")
    private String nickname;

    @Builder
    public UpdateNicknameRequest(String nickname) {
        this.nickname = nickname;
    }


    public UpdateNicknameData toService(Long userId) {
        return UpdateNicknameData.builder()
                .userId(userId)
                .nickname(nickname)
                .build();
    }
}
