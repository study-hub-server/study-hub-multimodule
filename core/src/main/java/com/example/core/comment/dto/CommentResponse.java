package com.example.core.comment.dto;

import com.example.core.comment.dao.CommentDao;
import com.example.core.study.dao.UserDao;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private final Long commentId;
    private final String content;
    private final LocalDateTime createdDate;
    private final boolean isUsersComment;
    private final UserDao commentedUserData;

    public CommentResponse(CommentDao commentDao) {
        this.commentId = commentDao.getCommentId();
        this.content = commentDao.getContent();
        this.createdDate = commentDao.getCreatedDate();
        this.isUsersComment = commentDao.isUsersComment();
        this.commentedUserData = commentDao.getCommentedUserData();
    }
}
