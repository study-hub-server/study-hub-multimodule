package com.example.webclient.api.user.dto.response;

import com.example.core.common.enums.GenderType;
import com.example.core.common.enums.MajorType;
import com.example.core.user.dto.data.UserDetailData;
import lombok.Getter;

@Getter
public class UserDetailResponse {

    private final Long postCount;
    private final Long participateCount;
    private final Long applyCount;
    private final String nickname;
    private final MajorType major;
    private final GenderType gender;
    private final String email;
    private final String imageUrl;

    public UserDetailResponse(UserDetailData userDetailData) {
        this.postCount = userDetailData.getPostCount();
        this.participateCount = userDetailData.getParticipateCount();
        this.applyCount = userDetailData.getApplyCount();
        this.nickname = userDetailData.getNickname();
        this.major = userDetailData.getMajor();
        this.gender = userDetailData.getGender();
        this.email = userDetailData.getEmail();
        this.imageUrl = userDetailData.getImageUrl();
    }
}
