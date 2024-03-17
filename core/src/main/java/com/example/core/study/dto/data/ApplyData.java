package com.example.core.study.dto.data;

import com.example.core.common.enums.Inspection;
import com.example.core.study.dao.StudyApplyDaoByUserId;
import com.example.core.study.domain.StudyApply;
import lombok.Getter;

@Getter
public class ApplyData {

    private final Long studyId;
    private final String studyTitle;
    private final Inspection inspection;
    private final String introduce;

    public ApplyData(StudyApplyDaoByUserId dao) {
        this.studyId = dao.getStudyId();
        this.studyTitle = dao.getStudyTitle();
        this.inspection = dao.getInspection();
        this.introduce = dao.getIntroduce();
    }
}
