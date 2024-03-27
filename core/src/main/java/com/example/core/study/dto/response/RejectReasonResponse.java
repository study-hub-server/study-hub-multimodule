package com.example.core.study.dto.response;

import com.example.core.study.dao.StudyApplyDaoByUserId;
import lombok.Getter;

@Getter
public class RejectReasonResponse {

    private final String studyTitle;
    private final String reason;

    public RejectReasonResponse(StudyApplyDaoByUserId dao) {
        this.studyTitle = dao.getStudyTitle();
        this.reason = dao.getRejectReason();
    }
}