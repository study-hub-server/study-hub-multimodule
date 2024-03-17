package com.example.core.user.domain;

import com.example.core.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserEntity extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserInfo userInfo;

    @Embedded
    private UserTypeInfo userTypeInfo;

    @Embedded
    private UserImage userImage;

    @Builder
    public UserEntity(UserInfo userInfo, UserTypeInfo userTypeInfo, UserImage userImage) {
        this.userInfo = userInfo;
        this.userTypeInfo = userTypeInfo;
        this.userImage = userImage;
    }
}
