package com.example.core.email.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class QuestionData {

    private final String title;
    private final String content;
    private final String toEmail;

    @Builder
    public QuestionData(String title, String content, String toEmail) {
        this.title = title;
        this.content = content;
        this.toEmail = toEmail;
    }
}
