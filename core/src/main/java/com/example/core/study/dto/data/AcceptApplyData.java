package com.example.core.study.dto.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AcceptApplyData {

    private final Long studyId;
    private final Long acceptUserId;

    @Builder
    public AcceptApplyData(Long studyId, Long acceptUserId) {
        this.studyId = studyId;
        this.acceptUserId = acceptUserId;
    }
}
