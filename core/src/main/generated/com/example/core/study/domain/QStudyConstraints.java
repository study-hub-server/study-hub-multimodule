package com.example.core.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyConstraints is a Querydsl query type for StudyConstraints
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QStudyConstraints extends BeanPath<StudyConstraints> {

    private static final long serialVersionUID = -728843500L;

    public static final QStudyConstraints studyConstraints = new QStudyConstraints("studyConstraints");

    public final BooleanPath close = createBoolean("close");

    public final EnumPath<com.example.core.common.enums.GenderType> filteredGender = createEnum("filteredGender", com.example.core.common.enums.GenderType.class);

    public final NumberPath<Integer> penalty = createNumber("penalty", Integer.class);

    public final StringPath penaltyWay = createString("penaltyWay");

    public final NumberPath<Integer> remainingSeat = createNumber("remainingSeat", Integer.class);

    public final NumberPath<Integer> studyPerson = createNumber("studyPerson", Integer.class);

    public final EnumPath<com.example.core.common.enums.StudyWayType> studyWay = createEnum("studyWay", com.example.core.common.enums.StudyWayType.class);

    public QStudyConstraints(String variable) {
        super(StudyConstraints.class, forVariable(variable));
    }

    public QStudyConstraints(Path<? extends StudyConstraints> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyConstraints(PathMetadata metadata) {
        super(StudyConstraints.class, metadata);
    }

}

