package dev.changuii.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QToiletEntity is a Querydsl query type for ToiletEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QToiletEntity extends EntityPathBase<ToiletEntity> {

    private static final long serialVersionUID = -14688143L;

    public static final QToiletEntity toiletEntity = new QToiletEntity("toiletEntity");

    public final NumberPath<Double> coordinateX = createNumber("coordinateX", Double.class);

    public final NumberPath<Double> coordinateY = createNumber("coordinateY", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<ReviewEntity, QReviewEntity> reviews = this.<ReviewEntity, QReviewEntity>createList("reviews", ReviewEntity.class, QReviewEntity.class, PathInits.DIRECT2);

    public QToiletEntity(String variable) {
        super(ToiletEntity.class, forVariable(variable));
    }

    public QToiletEntity(Path<? extends ToiletEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QToiletEntity(PathMetadata metadata) {
        super(ToiletEntity.class, metadata);
    }

}

