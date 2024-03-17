package com.example.core.study.dto.data;

import com.example.core.common.enums.GenderType;
import com.example.core.common.enums.MajorType;
import com.example.core.study.dao.StudyDaoByInquiry;
import com.example.core.study.dao.UserDao;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class StudyDataByInquiry {

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
    private final UserData userData;

    @Builder
    public StudyDataByInquiry(StudyDaoByInquiry studyDaoByInquiry) {
        this.postId = studyDaoByInquiry.getPostId();
        this.major = studyDaoByInquiry.getMajor();
        this.title = studyDaoByInquiry.getTitle();
        this.studyStartDate = studyDaoByInquiry.getStudyStartDate();
        this.studyEndDate = studyDaoByInquiry.getStudyEndDate();
        this.createdDate = studyDaoByInquiry.getCreatedDate();
        this.studyPerson = studyDaoByInquiry.getStudyPerson();
        this.filteredGender = studyDaoByInquiry.getFilteredGender();
        this.penalty = studyDaoByInquiry.getPenalty();
        this.penaltyWay = studyDaoByInquiry.getPenaltyWay();
        this.remainingSeat = studyDaoByInquiry.getRemainingSeat();
        this.close = studyDaoByInquiry.isClose();
        this.isBookmarked = studyDaoByInquiry.isBookmarked();
        this.userData = new UserData(studyDaoByInquiry.getUserDao());
    }
}