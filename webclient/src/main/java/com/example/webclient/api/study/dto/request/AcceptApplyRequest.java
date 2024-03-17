package com.example.webclient.api.study.dto.request;

import com.example.core.study.dto.data.AcceptApplyData;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AcceptApplyRequest {

    @Schema(description = "스터디 아이디")
    @NotNull(message = "스터디 아이디는 필수입니다!")
    private Long studyId;

    @Schema(description = "유저 아이디")
    @NotNull(message = "유저 아이디는 필수입니다!")
    private Long acceptUserId;

    public AcceptApplyData toAcceptApplyData() {
        return AcceptApplyData.builder()
                .studyId(studyId)
                .acceptUserId(acceptUserId)
                .build();
    }
}
