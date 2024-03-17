package com.example.core.study.repository;

import com.example.core.study.domain.StudyApply;
import com.example.core.study.domain.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudyRepository extends JpaRepository<StudyEntity, Long>, StudyRepositoryCustom{

    Long countByMasterUserId(Long userId);

    List<StudyEntity> findByMasterUserId(Long id);

    Optional<StudyEntity> findById(Long studyId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM StudyEntity sp WHERE sp.masterUserId = :userId")
    void deleteAllStudyByUserId(@Param("userId") Long userId);

}
