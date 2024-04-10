package com.example.core.study.dto.response;

import com.example.core.study.dto.data.ParticipateStudyData;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class FindParticipateStudyResponse {

    private Long totalCount;
    Slice<ParticipateStudyData> participateStudyData;

    public FindParticipateStudyResponse(Long totalCount, Slice<ParticipateStudyData> participateStudyData) {
        this.totalCount = totalCount;
        this.participateStudyData = participateStudyData;
    }
}
