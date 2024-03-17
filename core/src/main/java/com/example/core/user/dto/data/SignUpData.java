package com.example.core.user.dto.data;

import com.example.core.common.enums.GenderType;
import com.example.core.common.enums.MajorType;
import com.example.core.user.domain.UserEntity;
import com.example.core.user.domain.UserImage;
import com.example.core.user.domain.UserInfo;
import com.example.core.user.domain.UserTypeInfo;
import com.example.core.common.util.PasswordEncoder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpData {

    private String email;
    private String password;
    private String nickname;
    private MajorType major;
    private GenderType gender;
    private String imageUrl;

    public UserEntity toEntity(String basicProfile) {
        return UserEntity.builder()
                .userInfo(makeUserInfo())
                .userTypeInfo(makeUserType())
                .userImage(makeUserImage(basicProfile))
                .build();
    }

    public SignUpData(String nickname, String email, String password, MajorType major, GenderType gender) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.major = major;
        this.gender = gender;
    }

    private UserImage makeUserImage(String basicProfile) {
        return UserImage.builder()
                .imageUrl(basicProfile)
                .build();
    }

    private UserTypeInfo makeUserType() {
        return UserTypeInfo.builder()
                .major(major)
                .gender(gender)
                .build();
    }

    private UserInfo makeUserInfo() {
        return UserInfo.builder()
                .email(email)
                .password(PasswordEncoder.encode(email, password))
                .nickname(nickname)
                .build();
    }
}
