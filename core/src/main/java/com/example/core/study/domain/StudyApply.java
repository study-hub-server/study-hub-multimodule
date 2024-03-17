package com.example.core.study.domain;

import com.example.core.common.enums.Inspection;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.core.common.enums.Inspection.ACCEPT;
import static com.example.core.common.enums.Inspection.REJECT;

@Embeddable
@Getter
@NoArgsConstructor
public class StudyApply {

    @Enumerated(EnumType.STRING)
    private Inspection inspection;

    private String introduce;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "reject_reason")
    private String rejectReason;

    @Builder
    public StudyApply(Inspection inspection, String introduce, Long userId) {
        this.inspection = inspection;
        this.introduce = introduce;
        this.userId = userId;
        this.rejectReason = "";
    }

    public void acceptApply() {
        this.inspection = ACCEPT;
    }

    public void rejectApply(String rejectReason) {
        this.inspection = REJECT;
        this.rejectReason = rejectReason;
    }
}
