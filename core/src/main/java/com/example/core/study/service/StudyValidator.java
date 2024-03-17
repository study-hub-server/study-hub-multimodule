package com.example.core.study.service;

import com.example.core.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class StudyValidator {

    private final StudyRepository studyRepository;

    public void validateStudyExist(Long studyId) {
        studyRepository.findById(studyId).orElseThrow(RuntimeException::new);
    }
}
