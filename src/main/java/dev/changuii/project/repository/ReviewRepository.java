package dev.changuii.project.repository;

import dev.changuii.project.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    public int deleteReviewEntitiesById(long id);
    List<ReviewEntity> findByToilet_Id(Long id);
}
