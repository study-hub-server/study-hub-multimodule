package com.example.core.study.dto.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RejectApplyData {

    private final Long studyId;
    private final Long rejectedUserId;
    private final String rejectReason;

    @Builder
    public RejectApplyData(Long studyId, Long rejectedUserId, String rejectReason) {
        this.studyId = studyId;
        this.rejectedUserId = rejectedUserId;
        this.rejectReason = rejectReason;
    }
}
