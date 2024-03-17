package com.example.core.study.dto.response;

import com.example.core.study.dao.StudyDaoByInquiry;
import com.example.core.study.dto.data.StudyDataByInquiry;
import lombok.Getter;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;


import static com.example.core.common.util.Converter.convertDaoToData;

@Getter
public class FindStudyResponseByInquiry {

    Long totalCount;
    Slice<StudyDataByInquiry> postDataByInquiries;

    public FindStudyResponseByInquiry(Long totalCount, Slice<StudyDaoByInquiry> studyDaoByInquiry) {
        this.totalCount = totalCount;
        this.postDataByInquiries = new SliceImpl<>(convertDaoToData(studyDaoByInquiry, StudyDataByInquiry::new));
    }
}