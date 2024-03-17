package com.example.core.user.service;

import com.example.core.user.domain.UserEntity;
import com.example.core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserFinder {

    private final UserRepository userRepository;

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByUserInfo_Email(email).orElseThrow(RuntimeException::new);
    }
}
