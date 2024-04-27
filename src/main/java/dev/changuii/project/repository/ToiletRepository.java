package dev.changuii.project.repository;

import dev.changuii.project.entity.ToiletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToiletRepository extends JpaRepository<ToiletEntity, Long> {
    public List<ToiletEntity> findAllByCoordinateXBetweenAndCoordinateYBetween(Double x1, Double x2, Double y1, Double y2);
}
