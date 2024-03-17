package com.example.core.study.service;

import com.example.core.study.domain.StudyEntity;
import com.example.core.study.dto.data.CreateStudyData;
import com.example.core.study.dto.data.UpdateStudyData;
import com.example.core.study.repository.StudyRepository;
import com.example.core.user.domain.UserEntity;
import com.example.core.user.service.UserFinder;
import com.example.core.user.service.UserValidater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class StudyService {

    private final StudyRepository studyRepository;
    private final StudyFindService studyFindService;
    private final UserValidater userValidater;
    private final UserFinder userFinder;

    public Long createStudy(CreateStudyData data, Long userId) {
        userValidater.validateExistUserId(userId);
        StudyEntity study = data.toEntity(userId);
        return studyRepository.save(study).getId();
    }

    public Long updateStudy(UpdateStudyData data) {
        UserEntity user = userFinder.getUserById(data.getUserId());
        StudyEntity study = studyFindService.findStudy(data.getPostId());
        study.isStudyOfUser(user.getId());
        study.updateStudy(data);
        return study.getId();
    }

    public void deletePost(Long postId, Long userId) {
        UserEntity user = userFinder.getUserById(userId);
        StudyEntity study = studyFindService.findStudy(postId);
        study.isStudyOfUser(user.getId());
        studyRepository.delete(study);
    }

    public void closePost(Long postId, Long userId) {
        UserEntity user = userFinder.getUserById(userId);
        StudyEntity study = studyFindService.findStudy(postId);
        study.isStudyOfUser(user.getId());
        study.getStudyConstraints().close();
    }

    public void deleteAllStudyByUserId(Long userId) {
        userValidater.validateExistUserId(userId);
        studyRepository.deleteAllStudyByUserId(userId);
    }

}
