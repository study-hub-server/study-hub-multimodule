package com.example.core.study.dao;

import com.example.core.common.enums.GenderType;
import com.example.core.common.enums.MajorType;
import com.example.core.common.enums.StudyWayType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class StudyDaoInfo {

    private final Long postId;
    private final String title;
    private final LocalDateTime createdDate;
    private final String content;
    private final MajorType major;
    private final int studyPerson;
    private final GenderType filteredGender;
    private final StudyWayType studyWay;
    private final int penalty;
    private final String penaltyWay;
    private final LocalDate studyStartDate;
    private final LocalDate studyEndDate;
    private final String charUrl;
    private final int remainingSeat;
    private final boolean isUsersPost;
    private final boolean isBookmarked;
    private final Long studyId;
    private final boolean isClose;
    private final UserDao userDao;

    @Builder
    public StudyDaoInfo(Long postId, String title, LocalDateTime createdDate, String content, MajorType major, int studyPerson, GenderType filteredGender, StudyWayType studyWay, int penalty, String penaltyWay, LocalDate studyStartDate, LocalDate studyEndDate, String charUrl, int remainingSeat, boolean isUsersPost, boolean isBookmarked, Long studyId, boolean isClose, UserDao userDao) {
        this.postId = postId;
        this.title = title;
        this.createdDate = createdDate;
        this.content = content;
        this.major = major;
        this.studyPerson = studyPerson;
        this.filteredGender = filteredGender;
        this.studyWay = studyWay;
        this.penalty = penalty;
        this.penaltyWay = penaltyWay;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
        this.charUrl = charUrl;
        this.remainingSeat = remainingSeat;
        this.isUsersPost = isUsersPost;
        this.isBookmarked = isBookmarked;
        this.studyId = studyId;
        this.isClose = isClose;
        this.userDao = userDao;
    }
}
