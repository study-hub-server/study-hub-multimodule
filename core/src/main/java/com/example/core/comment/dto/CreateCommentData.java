package com.example.core.comment.dto;

import com.example.core.comment.domain.CommentEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentData {

    private Long postId;

    private String content;

    public CommentEntity toEntity(Long userId) {
        return CommentEntity.builder()
                .postId(postId)
                .userId(userId)
                .content(content)
                .build();
    }

    @Builder
    public CreateCommentData(Long postId, String content) {
        this.postId = postId;
        this.content = content;
    }
}
