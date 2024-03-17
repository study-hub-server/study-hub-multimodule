package com.example.core.user.domain;

import com.example.core.common.enums.GenderType;
import com.example.core.common.enums.MajorType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class UserTypeInfo {

    @Enumerated(EnumType.STRING)
    private MajorType major;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Builder
    public UserTypeInfo(MajorType major, GenderType gender) {
        this.major = major;
        this.gender = gender;
    }

    public void updateMajor(MajorType major) {
        this.major = major;
    }
}
