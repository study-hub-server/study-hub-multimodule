package com.example.core.user.dto.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateNicknameData {

    private Long userId;
    private String nickname;

    @Builder
    public UpdateNicknameData(Long userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }
}
