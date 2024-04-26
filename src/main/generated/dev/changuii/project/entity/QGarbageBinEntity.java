package dev.changuii.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGarbageBinEntity is a Querydsl query type for GarbageBinEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGarbageBinEntity extends EntityPathBase<GarbageBinEntity> {

    private static final long serialVersionUID = -1813041482L;

    public static final QGarbageBinEntity garbageBinEntity = new QGarbageBinEntity("garbageBinEntity");

    public final StringPath address = createString("address");

    public final StringPath detail = createString("detail");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ReviewEntity, QReviewEntity> reviews = this.<ReviewEntity, QReviewEntity>createList("reviews", ReviewEntity.class, QReviewEntity.class, PathInits.DIRECT2);

    public final StringPath type = createString("type");

    public QGarbageBinEntity(String variable) {
        super(GarbageBinEntity.class, forVariable(variable));
    }

    public QGarbageBinEntity(Path<? extends GarbageBinEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGarbageBinEntity(PathMetadata metadata) {
        super(GarbageBinEntity.class, metadata);
    }

}

