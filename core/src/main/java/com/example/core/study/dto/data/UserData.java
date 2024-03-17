package com.example.core.study.dto.data;

import com.example.core.common.enums.MajorType;
import com.example.core.study.dao.UserDao;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserData {

    private final Long userId;
    private final MajorType major;
    private final String nickname;
    private final String imageUrl;

    @Builder
    public UserData(UserDao userDao) {
        this.userId = userDao.getUserId();
        this.major = userDao.getMajor();
        this.nickname = userDao.getNickname();
        this.imageUrl = userDao.getImageUrl();
    }
}
