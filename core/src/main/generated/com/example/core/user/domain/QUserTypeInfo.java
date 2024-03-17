package com.example.core.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserTypeInfo is a Querydsl query type for UserTypeInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserTypeInfo extends BeanPath<UserTypeInfo> {

    private static final long serialVersionUID = 1748430232L;

    public static final QUserTypeInfo userTypeInfo = new QUserTypeInfo("userTypeInfo");

    public final EnumPath<com.example.core.common.enums.GenderType> gender = createEnum("gender", com.example.core.common.enums.GenderType.class);

    public final EnumPath<com.example.core.common.enums.MajorType> major = createEnum("major", com.example.core.common.enums.MajorType.class);

    public QUserTypeInfo(String variable) {
        super(UserTypeInfo.class, forVariable(variable));
    }

    public QUserTypeInfo(Path<? extends UserTypeInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserTypeInfo(PathMetadata metadata) {
        super(UserTypeInfo.class, metadata);
    }

}

