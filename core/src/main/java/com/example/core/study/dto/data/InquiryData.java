package com.example.core.study.dto.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class InquiryData {

    private final String inquiryText;
    private final boolean titleAndMajor;
    private final boolean hot;

    @Builder
    public InquiryData(String inquiryText, boolean titleAndMajor, boolean hot) {
        this.inquiryText = inquiryText;
        this.titleAndMajor = titleAndMajor;
        this.hot = hot;
    }
}
