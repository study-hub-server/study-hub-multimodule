package com.example.webclient.api.study.dto.request;

import com.example.core.study.dto.data.RejectApplyData;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RejectApplyRequest {

    @Schema(description = "스터디 아이디")
    @NotNull(message = "스터디 아이디는 필수입니다!")
    private Long studyId;

    @Schema(description = "거절당하는 유저 아이디")
    @NotNull(message = "거절된 유저 아이디는 필수입니다!")
    private Long rejectedUserId;

    @Schema(description = "거절 사유", example = "ㄴㄴ")
    @NotBlank(message = "거절 사유는 필수입니다!")
    private String rejectReason;

    public RejectApplyData toRejectEntity() {
        return RejectApplyData.builder()
                .studyId(studyId)
                .rejectedUserId(rejectedUserId)
                .rejectReason(rejectReason)
                .build();
    }
}
