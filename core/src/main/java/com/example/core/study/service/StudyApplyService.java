package com.example.core.study.service;

import com.example.core.common.util.Converter;
import com.example.core.study.dao.StudyApplyDaoByUserId;
import com.example.core.study.domain.StudyApply;
import com.example.core.study.domain.StudyEntity;
import com.example.core.study.dto.data.AcceptApplyData;
import com.example.core.study.dto.data.EnrollApplyData;
import com.example.core.study.dto.data.RejectApplyData;
import com.example.core.study.dto.response.FindMyApplyResponse;
import com.example.core.study.dto.response.FindParticipateStudyResponse;
import com.example.core.study.dto.response.RejectReasonResponse;
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

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class StudyApplyService {

    private final StudyRepository studyRepository;
    private final StudyValidator studyValidator;
    private final UserValidater userValidater;
    private final UserFinder userFinder;

    public void enroll(EnrollApplyData data, Long userId) {
        userValidater.validateExistUserId(userId);
        StudyEntity study = studyRepository.findById(data.getStudyId()).orElseThrow();
        study.validateApply(userId);
        List<StudyApply> applies = new ArrayList<>(study.getStudyApplies());
        applies.add(data.toStudyApply(userId, data.getIntroduce()));

        study.addApply(applies);
    }

    public void acceptApply(AcceptApplyData data, Long userId) {
        userValidater.validateExistUserId(userId);
        StudyEntity study = studyRepository.findById(data.getStudyId()).orElseThrow();
        study.acceptApply(data.getAcceptUserId());
    }

    public void rejectApply(RejectApplyData data, Long userId) {
        userValidater.validateExistUserId(userId);
        userValidater.validateExistUserId(data.getRejectedUserId());
        StudyEntity study = studyRepository.findById(data.getStudyId()).orElseThrow();
        study.rejectApply(data.getRejectedUserId(), data.getRejectReason());
    }

    public void deleteMyStudy(Long userId, Long studyId) {
        userValidater.validateExistUserId(userId);
        studyValidator.validateStudyExist(studyId);
        StudyEntity study = studyRepository.findById(studyId).orElseThrow();
        study.deleteApply(userId);
    }

    @Transactional(readOnly = true)
    public FindMyApplyResponse findApply(Long userId, final int page, final int size) {
        userValidater.validateExistUserId(userId);
        Slice<StudyApplyDaoByUserId> applyDao = Converter.toSlice(PageRequest.of(page, size), studyRepository.findApplyByUserId(userId));
        return new FindMyApplyResponse(5L, applyDao);
    }

    public RejectReasonResponse findRejectReason(Long userId, Long studyId) {
        userValidater.validateExistUserId(userId);
        studyValidator.validateStudyExist(studyId);
        StudyApplyDaoByUserId studyApplyDaoByUserId = studyRepository.findApplyByStudyIdAndUserId(studyId, userId);
        return new RejectReasonResponse(studyApplyDaoByUserId);
    }

//    public FindParticipateStudyResponse getParticipateStudy(Long userId, int page, int size) {
//        UserEntity user = userFinder.getUserById(userId);
//        Pageable pageable = PageRequest.of(page, size);
//
//    }
}
