package com.example.core.comment.service;

import com.example.core.comment.dao.CommentDao;
import com.example.core.comment.domain.CommentEntity;
import com.example.core.comment.dto.CommentResponse;
import com.example.core.comment.repository.CommentRepository;
import com.example.core.common.util.Converter;
import com.example.core.study.service.StudyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentFindService {
    private static final int COMMENT_PREVIEW_COUNT = 8;
    private final CommentRepository commentRepository;
    private final StudyValidator studyValidator;

    public CommentEntity findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(RuntimeException::new);
    }

    public List<CommentResponse> getPreviewComments(Long postId, Long userId) {
        return mapToCommentResponse(commentRepository.findPreviewByPostId(postId, userId, COMMENT_PREVIEW_COUNT));
    }

    public Slice<CommentResponse> getComments(Long postId, int page, int size, Long userId) {
        Pageable pageable = makePageable(page, size);
        studyValidator.validateStudyExist(postId);

        List<CommentDao> commentDaos = commentRepository.findSliceByPostIdWithUserId(postId, userId, pageable);
        return Converter.toSlice(pageable, mapToCommentResponse(commentDaos));
    }

    private Pageable makePageable(int page, int size) {
        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
    }

    private List<CommentResponse> mapToCommentResponse(List<CommentDao> commentDaos) {
        return commentDaos.stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }
}
