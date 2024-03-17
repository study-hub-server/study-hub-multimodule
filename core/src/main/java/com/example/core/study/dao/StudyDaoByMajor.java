package com.example.core.study.dao;

import com.example.core.common.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StudyDaoByMajor {

    private final Long studyId;
    private final String title;
    private final MajorType major;
    private final int remainingSeat;
    private final UserDao userDao;

    @Builder
    public StudyDaoByMajor(Long studyId, String title, MajorType major, int remainingSeat, UserDao userDao) {
        this.studyId = studyId;
        this.title = title;
        this.major = major;
        this.remainingSeat = remainingSeat;
        this.userDao = userDao;
    }
}
