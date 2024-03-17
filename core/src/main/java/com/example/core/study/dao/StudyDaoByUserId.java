package com.example.core.study.dao;

import com.example.core.common.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StudyDaoByUserId {

    private final Long postId;
    private final MajorType major;
    private final String title;
    private final String content;
    private final Integer remainingSeat;
    private final boolean close;
    private final Long studyId;

    @Builder
    public StudyDaoByUserId(Long postId, MajorType major, String title, String content, Integer remainingSeat, boolean close, Long studyId) {
        this.postId = postId;
        this.major = major;
        this.title = title;
        this.content = content;
        this.remainingSeat = remainingSeat;
        this.close = close;
        this.studyId = studyId;
    }
}
