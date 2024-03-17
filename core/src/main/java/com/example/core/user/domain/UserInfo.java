package com.example.core.user.domain;

import com.example.core.user.dto.data.SignInData;
import com.example.core.user.dto.data.UpdatePasswordData;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.core.common.util.PasswordEncoder.encode;
import static com.example.core.common.util.PasswordEncoder.matches;

@Embeddable
@Getter
@NoArgsConstructor
public class UserInfo {

    private String email;

    private String password;

    private String nickname;

    @Builder
    public UserInfo(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePassword(UpdatePasswordData data) {
        if (!data.isAuth()) {
            throw new RuntimeException();
        }
        this.password = encode(data.getEmail(), data.getPassword());
    }

    public void userVerify(SignInData signInRequest) {
        String userPassword = encode(signInRequest.getEmail(), signInRequest.getPassword());

        if (!matches(userPassword, this.password)) {
            throw new RuntimeException();
        }
    }
}
