package com.example.core.study.fixture;

import com.example.core.common.enums.Inspection;
import com.example.core.study.domain.StudyApply;
import com.example.core.study.domain.StudyEntity;
import com.example.core.study.domain.StudyInfo;

import java.util.ArrayList;
import java.util.List;

import static com.example.core.common.enums.MajorType.ARCHITECTURAL_ENGINEERING;

public class StudyEntityFixture {

    public static StudyEntity makeStudyEntity(Long masterUserId) {
        StudyInfo studyInfo = StudyInfo.builder()
                .title("SQLD 스터디원 구합니다.")
                .major(ARCHITECTURAL_ENGINEERING)
                .build();

        StudyApply studyApply = StudyApply.builder()
                .inspection(Inspection.STANDBY)
                .userId(masterUserId)
                .introduce("잘할게요..")
                .build();
        List<StudyApply> applies = new ArrayList<>();
        applies.add(studyApply);

        StudyEntity study = StudyEntity.builder()
                .studyInfo(studyInfo)
                .masterUserId(masterUserId)
                .build();
        study.addApply(applies);

        return study;
    }
}
