package com.example.core.comment.service;

import com.example.core.comment.domain.CommentEntity;
import com.example.core.comment.dto.CreateCommentData;
import com.example.core.comment.dto.UpdateCommentData;
import com.example.core.comment.repository.CommentRepository;
import com.example.core.study.service.StudyValidator;
import com.example.core.user.service.UserValidater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentFindService commentFindService;
    private final StudyValidator studyValidator;
    private final UserValidater userValidater;

    public void createComment(CreateCommentData request, Long userId) {
        studyValidator.validateStudyExist(request.getPostId());
        userValidater.validateExistUserId(userId);
        CommentEntity comment = request.toEntity(userId);
        commentRepository.save(comment);
    }

    public void updateComment(UpdateCommentData request, Long userId) {
        userValidater.validateExistUserId(userId);
        CommentEntity comment = commentFindService.findComment(request.getCommentId());
        comment.isCommentOfUser(userId);
        comment.updateCommentContent(request.getContent());
    }

    public void deleteComment(Long commentId, Long userId) {
        userValidater.validateExistUserId(userId);
        CommentEntity comment = commentFindService.findComment(commentId);
        comment.isCommentOfUser(userId);
        commentRepository.delete(comment);
    }
}
