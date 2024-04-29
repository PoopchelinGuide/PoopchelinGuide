package dev.changuii.project.dao;

import dev.changuii.project.entity.GarbageBinEntity;
import dev.changuii.project.entity.ReviewEntity;
import dev.changuii.project.entity.ToiletEntity;

import java.util.List;

public interface ReviewDAO {


    public List<ReviewEntity> readAllReviewByGarbageBin(GarbageBinEntity garbageBin);
    public List<ReviewEntity> readAllReviewByToilet(ToiletEntity toilet);
    public ReviewEntity createReivew(ReviewEntity reviewEntity);
    public ReviewEntity readByIdReview(Long id);
    public void deleteReview(Long id);
}
