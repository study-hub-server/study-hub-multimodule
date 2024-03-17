package com.example.core.study.dao;

import com.example.core.common.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDao {

    private final Long userId;
    private final MajorType major;
    private final String nickname;
    private final String imageUrl;

    @Builder
    public UserDao(Long userId, MajorType major, String nickname, String imageUrl) {
        this.userId = userId;
        this.major = major;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}
