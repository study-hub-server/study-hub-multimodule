package com.example.core.study.dao;

import com.example.core.common.enums.Inspection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StudyApplyDaoByUserId {

    private final Long studyId;
    private final String studyTitle;
    private final Inspection inspection;
    private final String introduce;
    private final String rejectReason;

    @Builder
    public StudyApplyDaoByUserId(Long studyId, String studyTitle, Inspection inspection, String introduce, String rejectReason) {
        this.studyId = studyId;
        this.studyTitle = studyTitle;
        this.inspection = inspection;
        this.introduce = introduce;
        this.rejectReason = rejectReason;
    }
}
