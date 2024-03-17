package com.example.core.user.repository;

import com.example.core.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserInfo_Email(String email);

    boolean existsByUserInfo_Email(String email);

    Optional<UserEntity> findById(Long id);
}
