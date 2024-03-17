package com.example.core.bookmark.domain;

import com.example.core.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "bookmark")
public class BookmarkEntity extends BaseTimeEntity {

    @Id
    @Column(name = "bookmark_id")
    private Long id;

    @Column(name = "study_id")
    private Long studyId;

    @Column(name = "user_id")
    private Long userId;

    public BookmarkEntity(Long studyId, Long userId) {
        this.studyId = studyId;
        this.userId = userId;
    }
}
