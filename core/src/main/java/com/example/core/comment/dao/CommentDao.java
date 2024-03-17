package com.example.core.comment.dao;

import com.example.core.study.dao.UserDao;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDao {

    private final Long commentId;
    private final String content;
    private final LocalDateTime createdDate;
    private final boolean isUsersComment;
    private final UserDao commentedUserData;

    public CommentDao(Long commentId, String content, LocalDateTime createdDate, boolean isUsersComment, UserDao commentedUserData) {
        this.commentId = commentId;
        this.content = content;
        this.createdDate = createdDate;
        this.isUsersComment = isUsersComment;
        this.commentedUserData = commentedUserData;
    }
}
