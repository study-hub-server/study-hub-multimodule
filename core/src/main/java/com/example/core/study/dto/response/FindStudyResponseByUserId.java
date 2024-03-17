package com.example.core.study.dto.response;

import com.example.core.study.dao.StudyDaoByUserId;
import com.example.core.study.dto.data.StudyDataByInquiry;
import com.example.core.study.dto.data.StudyDataByUserId;
import lombok.Getter;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import static com.example.core.common.util.Converter.convertDaoToData;

@Getter
public class FindStudyResponseByUserId {

    Long totalCount;
    Slice<StudyDataByUserId> posts;

    public FindStudyResponseByUserId(Long totalCount, Slice<StudyDaoByUserId> studyDaoByUserId) {
        this.totalCount = totalCount;
        this.posts = new SliceImpl<>(convertDaoToData(studyDaoByUserId, StudyDataByUserId::new));;
    }
}
