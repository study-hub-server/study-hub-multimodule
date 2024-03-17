package com.example.core.study.repository;

import com.example.core.common.enums.MajorType;
import com.example.core.study.dao.StudyApplyDaoByUserId;
import com.example.core.study.dao.StudyDaoByInquiry;
import com.example.core.study.domain.StudyApply;
import com.example.core.study.domain.StudyEntity;
import com.example.core.study.domain.StudyInfo;
import com.example.core.study.dto.data.InquiryData;
import com.example.core.study.fixture.StudyEntityFixture;
import com.example.core.support.RepositoryTest;
import com.example.core.user.domain.UserEntity;
import com.example.core.user.domain.UserInfo;
import com.example.core.user.domain.UserTypeInfo;
import com.example.core.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static com.example.core.common.enums.MajorType.ARCHITECTURAL_ENGINEERING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RepositoryTest
@TestPropertySource(locations = "classpath:application-test.yml")
class StudyRepositoryTest {

    @Autowired
    StudyRepository studyRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void 스터디_저장() {
        StudyEntity study = StudyEntity.builder().build();
        studyRepository.save(study);
    }

    @Test
    void 스터디_단건조회() {
        StudyInfo studyInfo = StudyInfo.builder()
                .title("SQLD 스터디원 구합니다.")
                .build();

        StudyEntity study = StudyEntity.builder()
                .studyInfo(studyInfo)
                .build();
        StudyEntity studyId = studyRepository.save(study);

        StudyEntity findStudy = studyRepository.findById(studyId.getId()).orElseThrow();

        assertThat(findStudy.getStudyInfo().getTitle()).isEqualTo(studyInfo.getTitle());
    }

    @Test
    void 스터디_전체조회() {
        InquiryData inquiryData = InquiryData.builder()
                .hot(false)
                .titleAndMajor(true)
                .inquiryText("SQLD")
                .build();
        UserInfo userInfo = UserInfo.builder()
                .nickname("이영재")
                .build();
        UserTypeInfo userTypeInfo = UserTypeInfo.builder()
                .major(ARCHITECTURAL_ENGINEERING)
                .build();

        UserEntity user = UserEntity.builder()
                .userInfo(userInfo)
                .userTypeInfo(userTypeInfo)
                .build();

        StudyEntity study = StudyEntityFixture.makeStudyEntity(userRepository.save(user).getId());
        studyRepository.save(study);

        List<StudyDaoByInquiry> dao = studyRepository.findByInquiry(inquiryData, PageRequest.of(0,1), study.getMasterUserId());
        Assertions.assertThat(dao.size()).isEqualTo(1);
    }

    @Test
    void 회원이_신청한_스터디조회() {
        StudyEntity study = StudyEntityFixture.makeStudyEntity(1L);
        StudyApply apply = study.getStudyApplies().get(0);
        studyRepository.save(study);

        List<StudyApplyDaoByUserId> dao = studyRepository.findApplyByUserId(1L);

        assertAll(
                () -> assertEquals(dao.size(), 1),
                () -> assertEquals(dao.get(0).getStudyTitle(), study.getStudyInfo().getTitle()),
                () -> assertEquals(dao.get(0).getInspection(), apply.getInspection()),
                () -> assertEquals(dao.get(0).getIntroduce(), apply.getIntroduce())
        );
    }

}