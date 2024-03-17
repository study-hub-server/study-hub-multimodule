package com.example.core.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyApply is a Querydsl query type for StudyApply
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QStudyApply extends BeanPath<StudyApply> {

    private static final long serialVersionUID = 1737430732L;

    public static final QStudyApply studyApply = new QStudyApply("studyApply");

    public final EnumPath<com.example.core.common.enums.Inspection> inspection = createEnum("inspection", com.example.core.common.enums.Inspection.class);

    public final StringPath introduce = createString("introduce");

    public final StringPath rejectReason = createString("rejectReason");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QStudyApply(String variable) {
        super(StudyApply.class, forVariable(variable));
    }

    public QStudyApply(Path<? extends StudyApply> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyApply(PathMetadata metadata) {
        super(StudyApply.class, metadata);
    }

}

