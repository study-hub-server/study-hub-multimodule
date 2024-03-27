package com.example.core.study.service;

import com.example.core.bookmark.service.BookmarkService;
import com.example.core.common.enums.MajorType;
import com.example.core.common.util.Converter;
import com.example.core.study.dao.*;
import com.example.core.study.domain.StudyEntity;
import com.example.core.study.dto.response.*;
import com.example.core.study.dto.data.InquiryData;
import com.example.core.study.repository.StudyRepository;
import com.example.core.user.domain.UserEntity;
import com.example.core.user.service.UserFinder;
import com.example.core.user.service.UserValidater;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class StudyFindService {

    private final StudyRepository studyRepository;
    private final UserValidater userValidater;
    private final UserFinder userFinder;
    private final BookmarkService bookmarkService;

    public FindStudyResponseByInquiry findPostResponseByInquiry(InquiryData data, int page, int size, Long userId) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<StudyDaoByInquiry> studies = Converter.toSlice(pageable, studyRepository.findByInquiry(data, pageable, userId));
        return new FindStudyResponseByInquiry((long) studies.getContent().size(), studies);
    }

    public FindStudyResponseByBookmark getBookmarkedPosts(int page, int size, Long userId) {
        userValidater.validateExistUserId(userId);
        Pageable pageable = PageRequest.of(page, size);
        Long totalCount = bookmarkService.getBookmarkCountByUserId(userId);
        Slice<StudyDaoByBookmark> posts = Converter.toSlice(pageable, studyRepository.findPostsByBookmarked(userId, pageable));
        return new FindStudyResponseByBookmark(totalCount, posts);
    }

    public FindStudyResponseByUserId getMyPosts(int page, int size, Long userId) {
        UserEntity user = userFinder.getUserById(userId);
        Pageable pageable = PageRequest.of(page, size);
        Long totalCount = studyRepository.countByMasterUserId(userId);
        Slice<StudyDaoByUserId> posts = Converter.toSlice(pageable, studyRepository.findByMasterUserId(user.getId(), pageable));
        return new FindStudyResponseByUserId(totalCount, posts);
    }

    public FindStudyResponseById findStudyById(Long studyId, Long userId) {
        StudyDaoInfo dao = studyRepository.findStudyById(studyId, userId).orElseThrow(RuntimeException::new);
        boolean isApply = studyRepository.validateApplyByStudyIdAndUserId(studyId, userId);

        return new FindStudyResponseById(dao, getRelatedStudy(dao.getMajor(), studyId), isApply);
    }

    public List<StudyDaoByMajor> getRelatedStudy(MajorType major, Long exceptPostId) {
        return studyRepository.findByMajor(major, exceptPostId);
    }

    public StudyEntity findStudy(Long postId) {
        return studyRepository.findById(postId).orElseThrow(RuntimeException::new);
    }

}
