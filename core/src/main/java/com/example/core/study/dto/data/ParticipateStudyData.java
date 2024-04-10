package com.example.core.study.dto.data;

import com.example.core.common.enums.Inspection;
import com.example.core.common.enums.MajorType;
import lombok.Getter;

@Getter
public class ParticipateStudyData {

    private final String major;
    private final String title;
    private final String content;
    private final String chatUrl;
    private final String inspection;
    private final Long postId;
    private final Long studyId;

    public ParticipateStudyData(MajorType major, String title, String content, String chatUrl, Inspection inspection, Long postId, Long studyId) {
        this.major = major.getValue();
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.inspection = inspection.getValue();
        this.postId = postId;
        this.studyId = studyId;
    }
}
