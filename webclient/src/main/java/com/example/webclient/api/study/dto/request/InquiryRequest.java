package com.example.webclient.api.study.dto.request;

import com.example.core.study.dto.data.InquiryData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
public class InquiryRequest {

    @Nullable
    private String inquiryText;
    private boolean titleAndMajor;
    private boolean hot;

    public InquiryData toInquiryData() {
        return InquiryData.builder()
                .inquiryText(inquiryText)
                .titleAndMajor(titleAndMajor)
                .hot(hot)
                .build();
    }
}
