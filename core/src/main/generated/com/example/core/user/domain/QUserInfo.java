package com.example.core.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserInfo extends BeanPath<UserInfo> {

    private static final long serialVersionUID = 1058856766L;

    public static final QUserInfo userInfo = new QUserInfo("userInfo");

    public final StringPath email = createString("email");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public QUserInfo(String variable) {
        super(UserInfo.class, forVariable(variable));
    }

    public QUserInfo(Path<? extends UserInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserInfo(PathMetadata metadata) {
        super(UserInfo.class, metadata);
    }

}

