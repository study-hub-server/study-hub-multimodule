package com.example.core.study.dto.data;

import com.example.core.common.enums.MajorType;
import com.example.core.study.dao.StudyDaoByBookmark;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StudyDataByBookmark {

    private final Long studyId;
    private final MajorType major;
    private final String title;
    private final String content;
    private final int remainingSeat;
    private final boolean close;

    @Builder
    public StudyDataByBookmark(StudyDaoByBookmark studyDaoByBookmark) {
        this.studyId = studyDaoByBookmark.getStudyId();
        this.major = studyDaoByBookmark.getMajor();
        this.title = studyDaoByBookmark.getTitle();
        this.content = studyDaoByBookmark.getContent();
        this.remainingSeat = studyDaoByBookmark.getRemainingSeat();
        this.close = studyDaoByBookmark.isClose();
    }
}
