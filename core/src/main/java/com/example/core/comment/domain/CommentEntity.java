package com.example.core.comment.domain;

import com.example.core.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class CommentEntity extends BaseTimeEntity {

    @Id
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

    private String content;

    @Builder
    public CommentEntity(Long id, Long userId, Long postId, String content) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }

    public void updateCommentContent(String content) {
        this.content = content;
    }

    public void isCommentOfUser(Long userId) {
        if(!this.userId.equals(userId)) {
            throw new RuntimeException();
        }
    }
}
