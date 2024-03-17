package com.example.core.study.dto.response;

import com.example.core.study.dao.StudyDaoByBookmark;
import com.example.core.study.dto.data.StudyDataByBookmark;
import lombok.Getter;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import static com.example.core.common.util.Converter.convertDaoToData;

@Getter
public class FindStudyResponseByBookmark {

    Long totalCount;
    Slice<StudyDataByBookmark> getBookmarkedPostsData;

    public FindStudyResponseByBookmark(Long totalCount, Slice<StudyDaoByBookmark> studyDaoByBookmark) {
        this.totalCount = totalCount;
        this.getBookmarkedPostsData = new SliceImpl<>(convertDaoToData(studyDaoByBookmark, StudyDataByBookmark::new));
    }
}
