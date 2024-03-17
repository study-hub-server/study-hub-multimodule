package com.example.core.study.dto.data;

import com.example.core.common.enums.GenderType;
import com.example.core.common.enums.MajorType;
import com.example.core.common.enums.StudyWayType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateStudyData {

    private final Long postId;
    private final Long userId;
    private final String title;
    private final String content;
    private final String chatUrl;
    private final MajorType major;
    private final int studyPerson;
    private final int penalty;
    private final String penaltyWay;
    private final boolean close;
    private final GenderType gender;
    private final StudyWayType studyWay;
    private final LocalDate studyStartDate;
    private final LocalDate studyEndDate;

    @Builder
    public UpdateStudyData(Long postId, Long userId, String title, String content, String chatUrl, MajorType major, int studyPerson, int penalty, String penaltyWay, boolean close, GenderType gender, StudyWayType studyWay, LocalDate studyStartDate, LocalDate studyEndDate) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.major = major;
        this.studyPerson = studyPerson;
        this.penalty = penalty;
        this.penaltyWay = penaltyWay;
        this.close = close;
        this.gender = gender;
        this.studyWay = studyWay;
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
    }
}
