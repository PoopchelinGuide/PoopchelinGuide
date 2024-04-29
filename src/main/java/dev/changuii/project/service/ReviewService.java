package dev.changuii.project.service;

import dev.changuii.project.dto.ReviewDTO;
import dev.changuii.project.dto.response.ResponseReviewDTO;

import java.util.List;

public interface ReviewService {

    public List<ResponseReviewDTO> readAllReviewByToiletORGarbageBin(boolean type, Long id);

    public ResponseReviewDTO createReview(ReviewDTO reviewDTO);
    public ResponseReviewDTO readReview(long id);
    public ResponseReviewDTO updateReview(long id, ReviewDTO reviewDTO);
    public void deleteReview(long id);


}
