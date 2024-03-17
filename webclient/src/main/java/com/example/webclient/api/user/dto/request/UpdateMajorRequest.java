package com.example.webclient.api.user.dto.request;

import com.example.core.common.enums.MajorType;
import com.example.core.user.dto.data.UpdateMajorData;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMajorRequest {

    private MajorType major;

    @Builder
    public UpdateMajorRequest(MajorType major) {
        this.major = major;
    }

    public UpdateMajorData toService(Long userId) {
        return UpdateMajorData.builder()
                .userId(userId)
                .major(major)
                .build();
    }
}
