package com.example.core.study.dto.data;

import com.example.core.common.enums.MajorType;
import com.example.core.study.dao.StudyDaoByMajor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StudyDataByMajor {

    private final Long studyId;
    private final String title;
    private final MajorType major;
    private final int remainingSeat;
    private final UserData userData;

    @Builder
    public StudyDataByMajor(StudyDaoByMajor studyDaoByMajor) {
        this.studyId = studyDaoByMajor.getStudyId();
        this.title = studyDaoByMajor.getTitle();
        this.major = studyDaoByMajor.getMajor();
        this.remainingSeat = studyDaoByMajor.getRemainingSeat();
        this.userData = new UserData(studyDaoByMajor.getUserDao());
    }
}
