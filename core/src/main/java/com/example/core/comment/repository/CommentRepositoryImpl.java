package com.example.core.comment.repository;

import com.example.core.comment.dao.CommentDao;
import com.example.core.study.dao.UserDao;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.core.comment.domain.QCommentEntity.commentEntity;
import static com.example.core.user.domain.QUserEntity.userEntity;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommentDao> findSliceByPostIdWithUserId(Long postId, Long userId, Pageable pageable) {
        return jpaQueryFactory
                .select(Projections.constructor(CommentDao.class,
                        commentEntity.id,
                        commentEntity.content,
                        commentEntity.createdDate,
                        loginPredicate(userId),
                        Projections.constructor(
                                UserDao.class,
                                userEntity.id,
                                userEntity.userTypeInfo.major,
                                userEntity.userInfo.nickname,
                                userEntity.userImage.imageUrl
                        )

                ))
                .from(commentEntity)
                .leftJoin(userEntity).on(commentEntity.userId.eq(userEntity.id))
                .where(commentEntity.postId.eq(postId))
                .orderBy(commentEntity.createdDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();
    }

    @Override
    public List<CommentDao> findPreviewByPostId(Long postId, Long userId, int commentPreviewCount) {
        JPAQuery<CommentDao> data = jpaQueryFactory
                .select(Projections.constructor(CommentDao.class,
                        commentEntity.id,
                        commentEntity.content,
                        commentEntity.createdDate,
                        loginPredicate(userId),
                        Projections.constructor(
                                UserDao.class,
                                userEntity.id,
                                userEntity.userTypeInfo.major,
                                userEntity.userInfo.nickname,
                                userEntity.userImage.imageUrl
                        )
                ))
                .from(commentEntity)
                .leftJoin(userEntity).on(commentEntity.userId.eq(userEntity.id))
                .where(commentEntity.postId.eq(postId))
                .orderBy(commentEntity.createdDate.desc())
                .limit(commentPreviewCount);
        data.fetch();

        return data.stream()
                .sorted(Comparator.comparing(CommentDao::getCreatedDate))
                .collect(Collectors.toList());
    }

    private BooleanExpression loginPredicate(Long userId) {
        if (userId != null) {
            return Expressions.booleanTemplate("{0} = {1}", commentEntity.userId, userId);
        }
        return Expressions.asBoolean(Expressions.constant(false));
    }
}
