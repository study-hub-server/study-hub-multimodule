package com.example.core.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserImage is a Querydsl query type for UserImage
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserImage extends BeanPath<UserImage> {

    private static final long serialVersionUID = -1535213365L;

    public static final QUserImage userImage = new QUserImage("userImage");

    public final StringPath imageUrl = createString("imageUrl");

    public QUserImage(String variable) {
        super(UserImage.class, forVariable(variable));
    }

    public QUserImage(Path<? extends UserImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserImage(PathMetadata metadata) {
        super(UserImage.class, metadata);
    }

}

