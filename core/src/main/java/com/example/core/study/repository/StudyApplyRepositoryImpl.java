//package com.example.core.study.repository;
//
//import com.example.core.study.dao.StudyApplyDaoByUserId;
//import com.example.core.study.dao.StudyDaoByInquiry;
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.impl.JPAQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.Query;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//
//import static com.example.core.study.domain.QStudyEntity.studyEntity;
//
//@RequiredArgsConstructor
//public class StudyApplyRepositoryImpl implements StudyApplyRepositoryCustom {
//
//    @PersistenceContext
//    private final EntityManager em;
//
//    @Override
//    public List<StudyApplyDaoByUserId> findApplyByUserId(Long userId) {
//        String sql = "SELECT s.study_id, s.title, sa.inspection, sa.introduce " +
//                "FROM study AS s INNER JOIN study_apply as sa ON s.study_id = sa.study_id " +
//                "WHERE sa.user_id = ?1";
//
//        Query query = em.createNativeQuery(sql, "StudyApplyDaoByUserIdMapping");
//        query.setParameter(1, userId);
//
//        return (List<StudyApplyDaoByUserId>) query.getResultList();
//    }
//}
