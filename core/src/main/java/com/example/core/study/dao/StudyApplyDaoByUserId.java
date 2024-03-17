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

    @Builder
    public StudyApplyDaoByUserId(Long studyId, String studyTitle, Inspection inspection, String introduce) {
        this.studyId = studyId;
        this.studyTitle = studyTitle;
        this.inspection = inspection;
        this.introduce = introduce;
    }
}
