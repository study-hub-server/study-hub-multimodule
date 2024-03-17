package com.example.webclient.api.study.dto.request;

import com.example.core.study.dto.data.EnrollApplyData;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EnrollApplyRequest {

    private Long studyId;
    private String introduce;

    public EnrollApplyData toEnrollApplyData() {
        return EnrollApplyData.builder()
                .studyId(studyId)
                .introduce(introduce)
                .build();
    }
}
