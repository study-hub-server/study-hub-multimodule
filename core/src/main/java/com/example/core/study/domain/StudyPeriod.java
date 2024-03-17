package com.example.core.study.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@Getter
public class StudyPeriod {

    @Column(name = "study_start_date")
    private LocalDate studyStartDate;

    @Column(name = "study_end_date")
    private LocalDate studyEndDate;

    @Builder
    public StudyPeriod(LocalDate studyStartDate, LocalDate studyEndDate) {
        validateStudyDate(studyStartDate, studyEndDate);
        this.studyStartDate = studyStartDate;
        this.studyEndDate = studyEndDate;
    }

    public void validateStudyDate(LocalDate studyStartDate, LocalDate studyEndDate) {
        LocalDate now = LocalDate.now();

        validateStartDateOverEndDate(studyStartDate, studyEndDate);
        validateStartDateBeforeNow(studyStartDate, now);
    }

    private void validateStartDateBeforeNow(LocalDate studyStartDate, LocalDate now) {
        if (now.isAfter(studyStartDate)) {
            throw new RuntimeException();
        }
    }

    private void validateStartDateOverEndDate(LocalDate studyStartDate, LocalDate studyEndDate) {
        if (studyStartDate.isAfter(studyEndDate)) {
            throw new RuntimeException();
        }
    }
}
