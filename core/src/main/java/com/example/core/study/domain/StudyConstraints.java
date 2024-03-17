package com.example.core.study.domain;

import com.example.core.common.enums.GenderType;
import com.example.core.common.enums.StudyWayType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Embeddable
@NoArgsConstructor
@Getter
public class StudyConstraints {

    @Enumerated(EnumType.STRING)
    @Column(name = "filtered_gender")
    private GenderType filteredGender;

    @Enumerated(EnumType.STRING)
    @Column(name = "study_way")
    private StudyWayType studyWay;

    private Integer penalty;

    @Column(name = "penalty_way")
    private String penaltyWay;

    @Column(name = "remaining_seat")
    private Integer remainingSeat;

    @Column(name = "study_person")
    private Integer studyPerson;

    @ColumnDefault("false")
    private boolean close;

    public void close() {
        this.close = true;
    }

    @Builder
    public StudyConstraints(GenderType filteredGender, StudyWayType studyWay, Integer penalty, String penaltyWay, Integer remainingSeat, Integer studyPerson, boolean close) {
        this.filteredGender = filteredGender;
        this.studyWay = studyWay;
        this.penalty = penalty;
        this.penaltyWay = penaltyWay;
        this.remainingSeat = remainingSeat;
        this.studyPerson = studyPerson;
        this.close = close;
    }
}
