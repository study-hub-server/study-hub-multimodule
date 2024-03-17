package com.example.core.study.repository;

import com.example.core.common.enums.MajorType;
import com.example.core.study.dao.*;
import com.example.core.study.domain.StudyApply;
import com.example.core.study.dto.data.InquiryData;
import com.example.core.study.dto.data.StudyDataByMajor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudyRepositoryCustom {

    List<StudyDaoByInquiry> findByInquiry(InquiryData data, Pageable pageable, Long userId);

    List<StudyDaoByBookmark> findPostsByBookmarked(Long userId, Pageable pageable);

    List<StudyDaoByUserId> findByMasterUserId(Long userId, Pageable pageable);

    Optional<StudyDaoInfo> findStudyById(Long postId, Long userId);

    List<StudyDaoByMajor> findByMajor(MajorType major, Long exceptPostId);

    List<String> findPostsByTitleStartWith(String keyword, int postRecommendCount);

    List<StudyApplyDaoByUserId> findApplyByUserId(Long userId);

}
