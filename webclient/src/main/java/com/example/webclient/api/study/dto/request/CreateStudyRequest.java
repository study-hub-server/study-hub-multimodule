package com.example.webclient.api.study.dto.request;

import com.example.core.common.enums.GenderType;
import com.example.core.common.enums.MajorType;
import com.example.core.common.enums.StudyWayType;
import com.example.core.study.dto.data.CreateStudyData;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CreateStudyRequest {

    @Schema(description = "제목", example = "정보처리기사")
    @NotBlank(message = "제목 작성은 필수입니다!")
    private String title;

    @Schema(description = "내용", example = "화이팅")
    @NotBlank(message = "내용 작성은 필수입니다!")
    private String content;

    @Schema(description = "채팅방 url", example = "www.dfasdf")
    @NotBlank(message = "채팅방 url 작성은 필수입니다!")
    private String chatUrl;

    @Schema(description = "관련 학과", example = "COMPUTER_SCIENCE_ENGINEERING")
    @NotNull(message = "관련 학과 작성은 필수입니다!")
    private MajorType major;

    @Schema(description = "스터디 정원", example = "10")
    private Integer studyPerson;

    @Schema(description = "벌금", example = "10000 (벌금이 없다면 0 보내주세요!)")
    @NotNull
    private Integer penalty;

    @Schema(description = "벌금 방식 없으면 null 보내주시면 됩니다", example = "지각비")
    private String penaltyWay;

    @Schema(description = "마감 여부", example = "false (default 값이 false 로 설정되어 있습니다, 따로 기입 안해주셔도 된다는 뜻)")
    private boolean close;

    @Schema(description = "필터 성별", example = "FEMALE")
    @NotNull(message = "허용 성별 작성은 필수입니다!")
    private GenderType gender;

    @Schema(description = "스터디 방식", example = "CONTACT")
    @NotNull(message = "스터디 방식 작성은 필수입니다!")
    private StudyWayType studyWay;

    @Schema(description = "스터디 시작 날짜(ISO 8601)", example = "2024-06-23")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "스터디 시작 날짜 작성은 필수입니다!")
    private LocalDate studyStartDate;

    @Schema(description = "스터디 종료 날짜(ISO 8601)", example = "2024-12-25")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "스터디 종료 날짜 작성은 필수입니다!")
    private LocalDate studyEndDate;

    public CreateStudyData toCreateStudyDate() {
        return CreateStudyData.builder()
                .title(title)
                .content(content)
                .chatUrl(chatUrl)
                .major(major)
                .studyPerson(studyPerson)
                .penalty(penalty)
                .penaltyWay(penaltyWay)
                .close(close)
                .gender(gender)
                .studyWay(studyWay)
                .studyStartDate(studyStartDate)
                .studyEndDate(studyEndDate)
                .build();
    }
}
