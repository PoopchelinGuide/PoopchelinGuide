package dev.changuii.project.service.impl;

import dev.changuii.project.dao.GarbageBinDAO;
import dev.changuii.project.dao.ReviewDAO;
import dev.changuii.project.dao.ToiletDAO;
import dev.changuii.project.dto.ReviewDTO;
import dev.changuii.project.dto.response.ResponsePopoverDTO;
import dev.changuii.project.dto.response.ResponseReviewDTO;
import dev.changuii.project.entity.GarbageBinEntity;
import dev.changuii.project.entity.ReviewEntity;
import dev.changuii.project.entity.ToiletEntity;
import dev.changuii.project.exception.InvalidPasswordException;
import dev.changuii.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewDAO reviewDAO;
    private final GarbageBinDAO garbageBinDAO;
    private final ToiletDAO toiletDAO;

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
    public List<ResponseReviewDTO> readAllReviewByToiletORGarbageBin(boolean type, Long id) {
        GarbageBinEntity garbageBin = type ? this.garbageBinDAO.readByIdGarbageBin(id) : null;
        ToiletEntity toilet = !type ? this.toiletDAO.readByIdToilet(id) : null;

        return ReviewEntity.toResponseDTOList(type ?
                        this.reviewDAO.readAllReviewByGarbageBin(garbageBin) :
                        this.reviewDAO.readAllReviewByToilet(toilet));
    }

    @Override
    public ResponsePopoverDTO readSummaryByToiletORGarbageBin(boolean type, Long id) {
        GarbageBinEntity garbageBin = type ? this.garbageBinDAO.readByIdGarbageBin(id) : null;
        ToiletEntity toilet = !type ? this.toiletDAO.readByIdToilet(id) : null;
        String name = type ? garbageBin.getName() : toilet.getName();

        List<ReviewEntity> entities = type ?
                this.reviewDAO.readAllReviewByGarbageBin(garbageBin) :
                this.reviewDAO.readAllReviewByToilet(toilet);

        Double avgRate = 0.0;
        Map<String, Integer> countTag = new HashMap<>();
        for(ReviewEntity e : entities){
            avgRate += e.getRate();
            for(String s : e.getTag()){
                countTag.put(s, countTag.containsKey(s) ? countTag.get(s)+1 : 1);
            }
        }

        List<String> resultTag = new ArrayList<>();
        List<ReviewEntity> resultEntity = new ArrayList<>();
        List<String> sort = new ArrayList<>(countTag.keySet());
        Collections.sort(sort, (o1, o2) -> {
            return  countTag.get(o2)- countTag.get(o1);
        });


        for(String s : sort){
            if(resultTag.size() == 3) break;
            resultTag.add(s);
        }

        avgRate /= entities.size() == 0 ? 1 : entities.size();

        for(ReviewEntity e : entities){
            if(resultEntity.size() == 2) break;
            resultEntity.add(e);
        }

        return ResponsePopoverDTO.builder()
                .name(name)
                .rate(avgRate)
                .Tag(resultTag)
                .recentReview(ReviewEntity.toResponseDTOList(resultEntity))
                .build();
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
    public void deleteReview(long id, String password) {
        ReviewEntity reviewEntity = reviewDAO.readByIdReview(id);

        if(!reviewEntity.getPassword().equals(password))
            throw new InvalidPasswordException();

        this.reviewDAO.deleteReview(id);
    }
}
