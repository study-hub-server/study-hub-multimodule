package com.example.core.study.dto.data;

import com.example.core.common.enums.GenderType;
import com.example.core.common.enums.MajorType;
import com.example.core.common.enums.StudyWayType;
import com.example.core.study.domain.StudyConstraints;
import com.example.core.study.domain.StudyEntity;
import com.example.core.study.domain.StudyInfo;
import com.example.core.study.domain.StudyPeriod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class CreateStudyData {

    private final String title;

    private final String content;

    private final String chatUrl;

    private final MajorType major;

    private final Integer studyPerson;

    private final Integer penalty;

    private final String penaltyWay;

    private final boolean close;

    private final GenderType gender;

    private final StudyWayType studyWay;

    private final LocalDate studyStartDate;

    private final LocalDate studyEndDate;

    public StudyEntity toEntity(Long masterUserId) {
        StudyInfo info = StudyInfo.builder()
                .title(title)
                .content(content)
                .major(major)
                .chatUrl(chatUrl)
                .build();

        StudyConstraints constraints = StudyConstraints.builder()
                .filteredGender(gender)
                .studyWay(studyWay)
                .penalty(penalty)
                .studyPerson(studyPerson)
                .close(close)
                .build();

        StudyPeriod period = StudyPeriod.builder()
                .studyStartDate(studyStartDate)
                .studyEndDate(studyEndDate)
                .build();

        return StudyEntity.builder()
                .studyInfo(info)
                .studyConstraints(constraints)
                .studyPeriod(period)
                .masterUserId(masterUserId)
                .build();
    }

    @Builder
    public CreateStudyData(String title, String content, String chatUrl, MajorType major, Integer studyPerson, Integer penalty, String penaltyWay, boolean close, GenderType gender, StudyWayType studyWay, LocalDate studyStartDate, LocalDate studyEndDate) {
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
