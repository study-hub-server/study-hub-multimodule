package com.example.core.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyInfo is a Querydsl query type for StudyInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QStudyInfo extends BeanPath<StudyInfo> {

    private static final long serialVersionUID = 333376912L;

    public static final QStudyInfo studyInfo = new QStudyInfo("studyInfo");

    public final StringPath chatUrl = createString("chatUrl");

    public final StringPath content = createString("content");

    public final EnumPath<com.example.core.common.enums.MajorType> major = createEnum("major", com.example.core.common.enums.MajorType.class);

    public final StringPath title = createString("title");

    public QStudyInfo(String variable) {
        super(StudyInfo.class, forVariable(variable));
    }

    public QStudyInfo(Path<? extends StudyInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyInfo(PathMetadata metadata) {
        super(StudyInfo.class, metadata);
    }

}

