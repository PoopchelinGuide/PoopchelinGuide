package dev.changuii.project.repository;

import dev.changuii.project.entity.GarbageBinEntity;
import dev.changuii.project.entity.ReviewEntity;
import dev.changuii.project.entity.ToiletEntity;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    public List<ReviewEntity> readAllByToiletOrderByWriteDateDesc(ToiletEntity toilet);
    public List<ReviewEntity> readAllByGarbageBinOrderByWriteDateDesc(GarbageBinEntity garbageBin);
    @Transactional
    public int deleteReviewEntitiesById(long id);
    List<ReviewEntity> findByToilet_Id(Long id);


    List<ReviewEntity> readRecentTwoByToiletOrderByWriteDateDesc(ToiletEntity toilet);

    List<ReviewEntity> readRecentTwoByGarbageBinOrderByWriteDateDesc(GarbageBinEntity garbageBin);
}
