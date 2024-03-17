package com.example.core.study.dto.response;

import com.example.core.common.enums.GenderType;
import com.example.core.common.enums.MajorType;
import com.example.core.common.enums.StudyWayType;
import com.example.core.study.dao.StudyDaoInfo;
import com.example.core.study.dao.UserDao;
import com.example.core.study.dto.data.StudyDataByMajor;
import com.example.core.study.dto.data.StudyDataInfo;
import com.example.core.study.dto.data.UserData;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class FindStudyResponseById {

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
    private final int remainingSeat;
    private final String chatUrl;
    private final boolean isUsersPost;
    private final boolean isBookmarked;
    private final Long studyId;
    private final boolean isClose;
    private final boolean isApply;
    private final UserData masterUser;
    private final List<StudyDataByMajor> relatedStudies;

    public FindStudyResponseById(StudyDaoInfo dao, List<StudyDataByMajor> relatedStudies, boolean isApply) {
        this.postId = dao.getPostId();
        this.title = dao.getTitle();
        this.createdDate = dao.getCreatedDate();
        this.content = dao.getContent();
        this.major = dao.getMajor();
        this.studyPerson = dao.getStudyPerson();
        this.filteredGender = dao.getFilteredGender();
        this.studyWay = dao.getStudyWay();
        this.penalty = dao.getPenalty();
        this.penaltyWay = dao.getPenaltyWay();
        this.studyStartDate = dao.getStudyStartDate();
        this.studyEndDate = dao.getStudyEndDate();
        this.remainingSeat = dao.getRemainingSeat();
        this.chatUrl = dao.getCharUrl();
        this.isUsersPost = dao.isUsersPost();
        this.isBookmarked = dao.isBookmarked();
        this.studyId = dao.getStudyId();
        this.isClose = dao.isClose();
        this.masterUser = new UserData(dao.getUserDao());
        this.relatedStudies = relatedStudies;
        this.isApply = isApply;
    }
}
