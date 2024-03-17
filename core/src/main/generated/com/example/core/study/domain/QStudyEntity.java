package com.example.core.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyEntity is a Querydsl query type for StudyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyEntity extends EntityPathBase<StudyEntity> {

    private static final long serialVersionUID = -1861436347L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyEntity studyEntity = new QStudyEntity("studyEntity");

    public final com.example.core.common.QBaseTimeEntity _super = new com.example.core.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> masterUserId = createNumber("masterUserId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final ListPath<StudyApply, QStudyApply> studyApplies = this.<StudyApply, QStudyApply>createList("studyApplies", StudyApply.class, QStudyApply.class, PathInits.DIRECT2);

    public final QStudyConstraints studyConstraints;

    public final QStudyInfo studyInfo;

    public final QStudyPeriod studyPeriod;

    public QStudyEntity(String variable) {
        this(StudyEntity.class, forVariable(variable), INITS);
    }

    public QStudyEntity(Path<? extends StudyEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyEntity(PathMetadata metadata, PathInits inits) {
        this(StudyEntity.class, metadata, inits);
    }

    public QStudyEntity(Class<? extends StudyEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyConstraints = inits.isInitialized("studyConstraints") ? new QStudyConstraints(forProperty("studyConstraints")) : null;
        this.studyInfo = inits.isInitialized("studyInfo") ? new QStudyInfo(forProperty("studyInfo")) : null;
        this.studyPeriod = inits.isInitialized("studyPeriod") ? new QStudyPeriod(forProperty("studyPeriod")) : null;
    }

}

