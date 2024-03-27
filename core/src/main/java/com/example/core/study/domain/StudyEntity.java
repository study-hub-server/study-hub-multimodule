package com.example.core.study.domain;

import com.example.core.common.BaseTimeEntity;
import com.example.core.common.enums.Inspection;
import com.example.core.study.dao.StudyApplyDaoByUserId;
import com.example.core.study.dto.data.UpdateStudyData;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SqlResultSetMapping(
        name = "StudyApplyDaoByUserIdMapping",
        classes = @ConstructorResult(
                targetClass = StudyApplyDaoByUserId.class,
                columns = {
                        @ColumnResult(name = "study_id", type = Long.class),
                        @ColumnResult(name = "title", type = String.class),
                        @ColumnResult(name = "inspection", type = Inspection.class),
                        @ColumnResult(name = "introduce", type = String.class),
                        @ColumnResult(name = "reject_reason", type = String.class)
                }
        )
)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "study")
public class StudyEntity extends BaseTimeEntity {

    @Id
    @Column(name = "study_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private StudyInfo studyInfo;

    @Embedded
    private StudyConstraints studyConstraints;

    @Embedded
    private StudyPeriod studyPeriod;

    @Column(name = "master_user_id")
    private Long masterUserId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "study_apply", joinColumns = @JoinColumn(name = "study_id"))
    private List<StudyApply> studyApplies;

    public void updateStudy(UpdateStudyData data) {
        StudyInfo info = StudyInfo.builder()
                .title(data.getTitle())
                .content(data.getContent())
                .major(data.getMajor())
                .chatUrl(data.getChatUrl())
                .build();

        StudyConstraints constraints = StudyConstraints.builder()
                .filteredGender(data.getGender())
                .studyWay(data.getStudyWay())
                .penalty(data.getPenalty())
                .studyPerson(data.getStudyPerson())
                .close(data.isClose())
                .build();

        StudyPeriod period = StudyPeriod.builder()
                .studyStartDate(data.getStudyStartDate())
                .studyEndDate(data.getStudyEndDate())
                .build();

        this.studyInfo = info;
        this.studyConstraints = constraints;
        this.studyPeriod = period;
    }

    @Builder
    public StudyEntity(StudyInfo studyInfo, StudyConstraints studyConstraints, StudyPeriod studyPeriod, Long masterUserId) {
        this.studyInfo = studyInfo;
        this.studyConstraints = studyConstraints;
        this.studyPeriod = studyPeriod;
        this.masterUserId = masterUserId;
    }

    public void isStudyOfUser(Long userId) {
        if(!this.masterUserId.equals(userId)) {
            throw new RuntimeException("해당 유저가 만든 스터디가 아닙니다.");
        }
    }

    public void validateApply(Long userId) {
        validateSameRequest(userId);
        validateClosedStudy();
    }

    private void validateClosedStudy() {
        if(studyConstraints.isClose()) {
            throw new RuntimeException("신청이 마감된 스터디입니다.");
        }
    }

    private void validateSameRequest(Long userId) {
        for(StudyApply apply : studyApplies) {
            if(apply.getUserId().equals(userId)) {
                return;
            }
        }
        throw new RuntimeException("이미 신청하신 스터디입니다.");
    }

    public void addApply(List<StudyApply> applies) {
        this.studyApplies = applies;
    }

    public void acceptApply(Long acceptId) {
        List<StudyApply> applies = new ArrayList<>();

        for(StudyApply apply : studyApplies) {
            if(apply.getUserId().equals(acceptId)) {
                apply.acceptApply();
                applies.add(apply);
                continue;
            }
            applies.add(apply);
        }

        this.studyApplies = applies;
    }

    public void rejectApply(Long userId, String rejectReason) {
        List<StudyApply> applies = new ArrayList<>();

        for(StudyApply apply : studyApplies) {
            if(apply.getUserId().equals(userId)) {
                apply.rejectApply(rejectReason);
                applies.add(apply);
                continue;
            }
            applies.add(apply);
        }

        this.studyApplies = applies;
    }

    public void deleteApply(Long userId) {
        studyApplies = studyApplies.stream()
                .filter(apply -> !apply.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
