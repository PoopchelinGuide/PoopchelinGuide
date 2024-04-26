package dev.changuii.project.repository;

import dev.changuii.project.entity.ToiletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToiletRepository extends JpaRepository<ToiletEntity, Long> {
}
