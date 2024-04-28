package dev.changuii.project.repository;

import dev.changuii.project.entity.GarbageBinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarbageBinRepository extends JpaRepository<GarbageBinEntity, Long> {
    List<GarbageBinEntity> findAllByCoordinateXBetweenAndCoordinateYBetween(Double x1, Double x2, Double y1, Double y2);
}
