package dev.changuii.project.dao;

import dev.changuii.project.entity.ReviewEntity;

public interface ReviewDAO {


    public ReviewEntity createReivew(ReviewEntity reviewEntity);
    public ReviewEntity readByIdReview(Long id);
    public void deleteReview(Long id);
}
