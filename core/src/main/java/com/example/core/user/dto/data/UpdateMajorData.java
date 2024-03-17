package com.example.core.user.dto.data;

import com.example.core.common.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateMajorData {

    private Long userId;
    private MajorType major;

    @Builder
    public UpdateMajorData(Long userId, MajorType major) {
        this.userId = userId;
        this.major = major;
    }
}
