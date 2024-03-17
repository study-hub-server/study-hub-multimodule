package com.example.core.email;

import io.kr.demo.infra.redis.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailCacheService {

    private static final int AUTH_CODE_LENGTH = 8;
    private static final int KEY_SIZE = 10;
    private final EmailRepository emailRepository;

    public String getAndCacheAuthCode(String email) {
        String authCode = generateAuthCode();
        emailRepository.saveCertificationNumber(email, authCode);

        return authCode;
    }

    private String generateAuthCode() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < AUTH_CODE_LENGTH; i++) {
            key.append(random.nextInt(KEY_SIZE));
        }

        return key.toString();
    }
}
