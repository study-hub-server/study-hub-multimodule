package com.example.core.study.domain;

import com.example.core.common.enums.MajorType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class StudyInfo {

    private String title;

    private String content;

    @Column(name = "chat_url")
    private String chatUrl;

    @Enumerated(EnumType.STRING)
    private MajorType major;

    @Builder
    public StudyInfo(String title, String content, String chatUrl, MajorType major) {
        this.title = title;
        this.content = content;
        this.chatUrl = chatUrl;
        this.major = major;
    }
}
