package com.example.core.comment.repository;

import com.example.core.comment.domain.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>, CommentRepositoryCustom {
}
