package com.example.core.study.dto.data;

import com.example.core.common.enums.MajorType;
import com.example.core.study.dao.StudyDaoByUserId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StudyDataByUserId {

    private final Long postId;
    private final MajorType major;
    private final String title;
    private final String content;
    private final Integer remainingSeat;
    private final boolean close;
    private final Long studyId;

    @Builder
    public StudyDataByUserId(StudyDaoByUserId studyDaoByUserId) {
        this.postId = studyDaoByUserId.getPostId();
        this.major = studyDaoByUserId.getMajor();
        this.title = studyDaoByUserId.getTitle();
        this.content = studyDaoByUserId.getContent();
        this.remainingSeat = studyDaoByUserId.getRemainingSeat();
        this.close = studyDaoByUserId.isClose();
        this.studyId = studyDaoByUserId.getStudyId();
    }
}
