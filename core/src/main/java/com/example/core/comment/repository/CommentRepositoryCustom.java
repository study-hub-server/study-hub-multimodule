package com.example.core.comment.repository;

import com.example.core.comment.dao.CommentDao;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentDao> findSliceByPostIdWithUserId(Long postId, Long userId, Pageable pageable);

    List<CommentDao> findPreviewByPostId(Long postId, Long userId, int commentPreviewCount);
}
