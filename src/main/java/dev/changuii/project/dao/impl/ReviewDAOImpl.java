package dev.changuii.project.dao.impl;

import dev.changuii.project.dao.ReviewDAO;
import dev.changuii.project.entity.GarbageBinEntity;
import dev.changuii.project.entity.ReviewEntity;
import dev.changuii.project.entity.ToiletEntity;
import dev.changuii.project.exception.DataNotFoundException;
import dev.changuii.project.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDAOImpl implements ReviewDAO {

    private final ReviewRepository reviewRepository;

    public ReviewDAOImpl(@Autowired ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewEntity> readAllReviewByGarbageBin(GarbageBinEntity garbageBin) {
        return this.reviewRepository.readAllByGarbageBin(garbageBin);
    }

    @Override
    public List<ReviewEntity> readAllReviewByToilet(ToiletEntity toilet) {
        return this.reviewRepository.readAllByToilet(toilet);
    }

    @Override
    public ReviewEntity createReivew(ReviewEntity reviewEntity) {
        return this.reviewRepository.save(reviewEntity);
    }

    @Override
    public ReviewEntity readByIdReview(Long id) {
        return this.reviewRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }

    @Override
    public void deleteReview(Long id) {
        if(this.reviewRepository.deleteReviewEntitiesById(id) == 0)
            throw new DataNotFoundException();

    }
}
