package com.example.core.user.domain;

import com.example.core.user.dto.data.UserActivityCountData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserActivityFinder {

//    private final StudyPostRepository studyPostRepository;
//    private final ApplyRepository applyRepository;

    public UserActivityCountData countUserActivity(Long userId) {
//        Long postCount = studyPostRepository.countByPostedUserId(userId);
//        Long participateCount = applyRepository.countByUserIdAndInspection(userId, ACCEPT);
//        Long applyCount = applyRepository.countByUserId(userId);
        return new UserActivityCountData(1L, 1L, 1L);
    }
}
