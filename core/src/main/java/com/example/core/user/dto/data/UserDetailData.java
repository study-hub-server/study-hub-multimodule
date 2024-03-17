package com.example.core.user.dto.data;

import com.example.core.common.enums.GenderType;
import com.example.core.common.enums.MajorType;
import com.example.core.user.domain.UserEntity;
import com.example.core.user.dto.data.UserActivityCountData;
import lombok.Getter;

@Getter
public class UserDetailData {

    private final Long postCount;
    private final Long participateCount;
    private final Long applyCount;
    private final String nickname;
    private final MajorType major;
    private final GenderType gender;
    private final String email;
    private final String imageUrl;

    public UserDetailData(UserEntity user, UserActivityCountData data) {
        this.postCount = data.getPostCount();
        this.participateCount = data.getParticipateCount();
        this.applyCount = data.getApplyCount();
        this.nickname = user.getUserInfo().getNickname();
        this.major = user.getUserTypeInfo().getMajor();
        this.gender = user.getUserTypeInfo().getGender();
        this.email = user.getUserInfo().getEmail();
        this.imageUrl = user.getUserImage().getImageUrl();
    }
}
