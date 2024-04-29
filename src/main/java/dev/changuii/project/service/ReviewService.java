package dev.changuii.project.service;

import dev.changuii.project.dto.ReviewDTO;
import dev.changuii.project.dto.response.ResponseReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ResponseReviewDTO> readAllReviewByToiletORGarbageBin(boolean type, Long id);

    ResponseReviewDTO createReview(ReviewDTO reviewDTO);
    ResponseReviewDTO readReview(long id);
    ResponseReviewDTO updateReview(long id, ReviewDTO reviewDTO);
    List<ResponseReviewDTO> readSummaryByToiletORGarbageBin(boolean type, Long id);
    void deleteReview(long id,String password);


}
