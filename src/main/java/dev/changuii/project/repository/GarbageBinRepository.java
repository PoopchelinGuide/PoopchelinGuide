package dev.changuii.project.repository;

import dev.changuii.project.entity.GarbageBinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarbageBinRepository extends JpaRepository<GarbageBinEntity, Long> {
}
