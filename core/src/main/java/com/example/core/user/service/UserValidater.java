package com.example.core.user.service;

import com.example.core.user.domain.UserEntity;
import com.example.core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.core.common.util.PasswordEncoder.encode;
import static com.example.core.common.util.PasswordEncoder.matches;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserValidater {

    private final UserRepository userRepository;

    public void validateExistUserEmail(String email) {
        if (userRepository.existsByUserInfo_Email(email)) {
            throw new RuntimeException();
        }
    }

    public void validateExistUserId(Long userId) {
        userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }

    public void verifyPassword(Long userId, String password) {
        UserEntity user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        if (!matches(encode(user.getUserInfo().getEmail(), password), user.getUserInfo().getPassword())) {
            throw new RuntimeException();
        }
    }
}
