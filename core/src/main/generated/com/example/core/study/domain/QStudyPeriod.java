package com.example.core.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyPeriod is a Querydsl query type for StudyPeriod
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QStudyPeriod extends BeanPath<StudyPeriod> {

    private static final long serialVersionUID = -1554887133L;

    public static final QStudyPeriod studyPeriod = new QStudyPeriod("studyPeriod");

    public final DatePath<java.time.LocalDate> studyEndDate = createDate("studyEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> studyStartDate = createDate("studyStartDate", java.time.LocalDate.class);

    public QStudyPeriod(String variable) {
        super(StudyPeriod.class, forVariable(variable));
    }

    public QStudyPeriod(Path<? extends StudyPeriod> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyPeriod(PathMetadata metadata) {
        super(StudyPeriod.class, metadata);
    }

}

