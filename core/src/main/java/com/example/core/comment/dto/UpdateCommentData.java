package com.example.core.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCommentData {

    private Long commentId;

    private String content;

    @Builder
    public UpdateCommentData(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }
}
