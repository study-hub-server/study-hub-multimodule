package com.example.core.study.dao;

import com.example.core.common.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StudyDaoByBookmark {

    private final Long studyId;
    private final MajorType major;
    private final String title;
    private final String content;
    private final int remainingSeat;
    private final boolean close;

    @Builder
    public StudyDaoByBookmark(Long studyId, MajorType major, String title, String content, int remainingSeat, boolean close) {
        this.studyId = studyId;
        this.major = major;
        this.title = title;
        this.content = content;
        this.remainingSeat = remainingSeat;
        this.close = close;
    }
}
