package dev.changuii.project.service.impl;

import dev.changuii.project.dao.GarbageBinDAO;
import dev.changuii.project.dao.ReviewDAO;
import dev.changuii.project.dao.ToiletDAO;
import dev.changuii.project.dto.ReviewDTO;
import dev.changuii.project.dto.response.ResponseReviewDTO;
import dev.changuii.project.entity.GarbageBinEntity;
import dev.changuii.project.entity.ReviewEntity;
import dev.changuii.project.entity.ToiletEntity;
import dev.changuii.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewDAO reviewDAO;
    private GarbageBinDAO garbageBinDAO;
    private ToiletDAO toiletDAO;

    public ReviewServiceImpl(
            @Autowired ReviewDAO reviewDAO,
            @Autowired GarbageBinDAO garbageBinDAO,
            @Autowired ToiletDAO toiletDAO
    ){
        this.reviewDAO = reviewDAO;
        this.garbageBinDAO = garbageBinDAO;
        this.toiletDAO = toiletDAO;
    }


    @Override
    public ResponseReviewDTO createReview(ReviewDTO reviewDTO) {
        GarbageBinEntity garbageBin = null;
        ToiletEntity toilet = null;

        if(reviewDTO.isType()){
            garbageBin = this.garbageBinDAO.readByIdGarbageBin(reviewDTO.getGarbageBinId());
        }else{
            toilet = this.toiletDAO.readByIdToilet(reviewDTO.getToiletId());
        }
        return ReviewEntity
                .toResponseDTO(this.reviewDAO.createReivew(ReviewDTO
                .toEntity(reviewDTO).setType(toilet, garbageBin)));
    }

    @Override
    public ResponseReviewDTO readReview(long id) {
        return ReviewEntity
                .toResponseDTO(this.reviewDAO.readByIdReview(id));
    }

    @Override
    public ResponseReviewDTO updateReview(long id, ReviewDTO reviewDTO) {
        // 기존 entity -> update -> 다시 save -> to REsponseDTO -> return
        return ReviewEntity.toResponseDTO(
                this.reviewDAO.createReivew(
                        this.reviewDAO.readByIdReview(id).updateReview(reviewDTO)));
    }

    @Override
    public void deleteReview(long id) {
        this.reviewDAO.deleteReview(id);
    }
}
