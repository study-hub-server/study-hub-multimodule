package com.example.core.study.dao;

import com.example.core.common.enums.Inspection;
import com.example.core.common.enums.MajorType;
import lombok.Getter;

@Getter
public class ParticipateStudyDaoByUserId {

    private final String major;
    private final String title;
    private final String content;
    private final String chatUrl;
    private final Long studyId;

    public ParticipateStudyDaoByUserId(MajorType major, String title, String content, String chatUrl, Long studyId) {
        this.major = major.getValue();
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.studyId = studyId;
    }
}
