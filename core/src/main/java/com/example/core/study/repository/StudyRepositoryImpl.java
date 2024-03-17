package com.example.core.study.repository;

import com.example.core.common.enums.MajorType;
import com.example.core.study.dao.*;
import com.example.core.study.dto.data.InquiryData;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.core.bookmark.domain.QBookmarkEntity.bookmarkEntity;
import static com.example.core.study.domain.QStudyEntity.studyEntity;
import static com.example.core.user.domain.QUserEntity.userEntity;

@RequiredArgsConstructor
public class StudyRepositoryImpl implements StudyRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private final EntityManager em;

    @Override
    public List<StudyDaoByInquiry> findByInquiry(InquiryData data, final Pageable pageable, Long userId) {
        JPAQuery<StudyDaoByInquiry> daoData = jpaQueryFactory
                .select(Projections.constructor(StudyDaoByInquiry.class,
                        studyEntity.id.as("postId"), studyEntity.studyInfo.major, studyEntity.studyInfo.title, studyEntity.studyPeriod.studyStartDate, studyEntity.studyPeriod.studyEndDate,
                        studyEntity.createdDate, studyEntity.studyConstraints.studyPerson, studyEntity.studyConstraints.filteredGender,
                        studyEntity.studyConstraints.penalty, studyEntity.studyConstraints.penaltyWay, studyEntity.studyConstraints.remainingSeat, studyEntity.studyConstraints.close,
                        bookmarkPredicate(userId),
                        Projections.constructor(
                                UserDao.class,
                                userEntity.id, userEntity.userTypeInfo.major, userEntity.userInfo.nickname, userEntity.userImage.imageUrl
                        )
                ))
                .from(studyEntity)
                .leftJoin(userEntity).on(studyEntity.masterUserId.eq(userEntity.id))
                .where(textEqMajorOrTitle(data.getInquiryText(), data.isTitleAndMajor()))
                .orderBy(studyEntity.studyConstraints.close.asc(), hotPredicate(data), studyEntity.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);

        if (userId != null) {
            daoData.leftJoin(bookmarkEntity).on(studyEntity.id.eq(bookmarkEntity.studyId).and(bookmarkEntity.userId.eq(userId)));
        }

        return daoData.fetch();
    }

    @Override
    public List<StudyDaoByBookmark> findPostsByBookmarked(Long userId, Pageable pageable) {
        JPAQuery<StudyDaoByBookmark> studyPostDto = jpaQueryFactory.select(
                        Projections.constructor(StudyDaoByBookmark.class,
                                studyEntity.id, studyEntity.studyInfo.major, studyEntity.studyInfo.title, studyEntity.studyInfo.content, studyEntity.studyConstraints.remainingSeat, studyEntity.studyConstraints.close))
                .from(studyEntity)
                .innerJoin(bookmarkEntity).on(bookmarkEntity.studyId.eq(studyEntity.id))
                .where(bookmarkEntity.userId.eq(userId))
                .orderBy(studyEntity.studyConstraints.close.asc(), studyEntity.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);

        return studyPostDto.fetch();
    }

    @Override
    public List<StudyDaoByUserId> findByMasterUserId(Long userId, Pageable pageable) {
        JPAQuery<StudyDaoByUserId> data = jpaQueryFactory.select(
                        Projections.constructor(StudyDaoByUserId.class,
                                studyEntity.id,
                                studyEntity.studyInfo.major,
                                studyEntity.studyInfo.title,
                                studyEntity.studyInfo.content,
                                studyEntity.studyConstraints.remainingSeat,
                                studyEntity.studyConstraints.close,
                                studyEntity.id
                        )
                )
                .from(studyEntity)
                .where(studyEntity.masterUserId.eq(userId))
                .orderBy(studyEntity.studyConstraints.close.asc(), studyEntity.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);


        return data.fetch();
    }

    @Override
    public Optional<StudyDaoInfo> findStudyById(Long postId, Long userId) {
        JPAQuery<StudyDaoInfo> data = jpaQueryFactory
                .select(Projections.constructor(
                        StudyDaoInfo.class,
                        studyEntity.id, studyEntity.studyInfo.title, studyEntity.createdDate, studyEntity.studyInfo.content, studyEntity.studyInfo.major,
                        studyEntity.studyConstraints.studyPerson, studyEntity.studyConstraints.filteredGender, studyEntity.studyConstraints.studyWay, studyEntity.studyConstraints.penalty, studyEntity.studyConstraints.penaltyWay,
                        studyEntity.studyPeriod.studyStartDate, studyEntity.studyPeriod.studyEndDate, studyEntity.studyInfo.chatUrl, studyEntity.studyConstraints.remainingSeat,
                        userId != null ? Expressions.booleanTemplate("{0} = {1}", studyEntity.masterUserId, userId) : Expressions.constant(false),
                        userId != null ? Expressions.booleanTemplate("{0} = {1}", bookmarkEntity.userId, userId) : Expressions.constant(false),
                        studyEntity.id, studyEntity.studyConstraints.close,
                        Projections.constructor(
                                UserDao.class,
                                userEntity.id, userEntity.userTypeInfo.major, userEntity.userInfo.nickname, userEntity.userImage.imageUrl
                        )
                ))
                .from(studyEntity)
                .leftJoin(userEntity).on(studyEntity.masterUserId.eq(userEntity.id));

        if (userId != null) {
            data.leftJoin(bookmarkEntity).on(studyEntity.id.eq(bookmarkEntity.studyId).and(bookmarkEntity.userId.eq(userId)));
        }

        StudyDaoInfo result = data.where(studyEntity.id.eq(postId)).fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public List<StudyDaoByMajor> findByMajor(MajorType major, Long exceptPostId) {
        JPAQuery<StudyDaoByMajor> data = jpaQueryFactory.select(
                        Projections.constructor(StudyDaoByMajor.class,
                                studyEntity.id,
                                studyEntity.studyInfo.title,
                                studyEntity.studyInfo.major,
                                studyEntity.studyConstraints.remainingSeat,
                                Projections.constructor(
                                        UserDao.class,
                                        userEntity.id,
                                        userEntity.userTypeInfo.major,
                                        userEntity.userInfo.nickname,
                                        userEntity.userImage.imageUrl
                                )
                        )
                )
                .from(studyEntity)
                .leftJoin(userEntity).on(studyEntity.masterUserId.eq(userEntity.id))
                .where(studyEntity.studyInfo.major.eq(major).and(studyEntity.id.ne(exceptPostId)))
                .orderBy(
                        studyEntity.studyConstraints.remainingSeat.asc(),
                        studyEntity.createdDate.desc()
                )
                .limit(5);

        return data.fetch();
    }

    @Override
    public List<String> findPostsByTitleStartWith(String keyword, int postRecommendCount) {
        return null;
    }

    private Predicate textEqMajorOrTitle(String inquiryText, boolean titleAndMajor) {
        if (inquiryText != null && !titleAndMajor) {
            return studyEntity.studyInfo.major.eq(MajorType.findMajorType(inquiryText));
        } else {
            return studyEntity.studyInfo.title.like(Objects.requireNonNullElse(inquiryText, "") + "%");
        }
    }

    private BooleanExpression textEq(String inquiryText, boolean titleAndMajor) {
        if (!titleAndMajor) {
            return studyEntity.studyInfo.title.contains(Objects.requireNonNullElse(inquiryText, ""));
        }
        return null;
//        return studyPostEntity.title.contains(Objects.requireNonNullElse(inquiryText, ""));
    }

    private Predicate majorEq(String inquiryText, boolean titleAndMajor) {
        if (inquiryText != null && titleAndMajor) {
            return studyEntity.studyInfo.major.eq(MajorType.findMajorType(inquiryText));
        }
        return null;
    }

    private OrderSpecifier<?> hotPredicate(InquiryData data) {
        if(data.isHot()) {
            return studyEntity.studyConstraints.remainingSeat.asc();
        }
        return studyEntity.createdDate.desc();
    }

    private BooleanExpression bookmarkPredicate(Long userId) {
        if (userId != null) {
            return Expressions.booleanTemplate("{0} = {1}", bookmarkEntity.userId, userId);
        }
        return Expressions.asBoolean(Expressions.constant(false));
    }

    @Override
    public List<StudyApplyDaoByUserId> findApplyByUserId(Long userId) {
        String sql = "SELECT s.study_id, s.title, sa.inspection, sa.introduce " +
                "FROM study AS s INNER JOIN study_apply as sa ON s.study_id = sa.study_id " +
                "WHERE sa.user_id = ?1";

        Query query = em.createNativeQuery(sql, "StudyApplyDaoByUserIdMapping");
        query.setParameter(1, userId);

        return (List<StudyApplyDaoByUserId>) query.getResultList();
    }
}
