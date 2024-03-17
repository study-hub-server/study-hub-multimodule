package com.example.core.email;

import com.example.core.email.dto.ValidMailInfo;
import io.kr.demo.infra.redis.EmailRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailValidater {

    private final EmailRepository emailRepository;

    public boolean validEmail(ValidMailInfo info) {
        String cachedAuthCode = emailRepository.validEmail(info.getEmail());
        return cachedAuthCode != null && cachedAuthCode.equals(info.getAuthCode());
    }
}
