package com.example.core.study.dao;

import com.example.core.common.enums.GenderType;
import com.example.core.common.enums.MajorType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class StudyDaoByInquiry {

    private final Long postId;
    private final MajorType major;
    private final String title;
    private final LocalDate studyStartDate;
    private final LocalDate studyEndDate;
    private final LocalDateTime createdDate;
    private final Integer studyPerson;
    private final GenderType filteredGender;
    private final Integer penalty;
    private final String penaltyWay;
    private final Integer remainingSeat;
    private final boolean close;
    private final boolean isBookmarked;
    private final UserDao userDao;

    @Builder
    public StudyDaoByInquiry(Long postId, MajorType major, String title, LocalDate studyStartDate, LocalDate studyEndDate, LocalDateTime createdDate, Integer studyPerson, GenderType filteredGender, Integer penalty, String penaltyWay, Integer remainingSeat, boolean close, boolean isBookmarked, UserDao userDao) {
        this.postId = postId;
        this.major = major;
        this.title = title;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
        this.createdDate = createdDate;
        this.studyPerson = studyPerson;
        this.filteredGender = filteredGender;
        this.penalty = penalty;
        this.penaltyWay = penaltyWay;
        this.remainingSeat = remainingSeat;
        this.close = close;
        this.isBookmarked = isBookmarked;
        this.userDao = userDao;
    }
}
