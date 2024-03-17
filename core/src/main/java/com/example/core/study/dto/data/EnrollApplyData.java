package com.example.core.study.dto.data;

import com.example.core.common.enums.Inspection;
import com.example.core.study.domain.StudyApply;
import lombok.Builder;
import lombok.Getter;

import static com.example.core.common.enums.Inspection.*;

@Getter
public class EnrollApplyData {

    private final Long studyId;
    private final String introduce;

    @Builder
    public EnrollApplyData(Long studyId, String introduce) {
        this.studyId = studyId;
        this.introduce = introduce;
    }

    public StudyApply toStudyApply(Long userId, String introduce) {
        return StudyApply.builder()
                .userId(userId)
                .inspection(STANDBY)
                .introduce(introduce)
                .build();
    }
}
