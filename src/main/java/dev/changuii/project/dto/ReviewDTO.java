package dev.changuii.project.dto;


import dev.changuii.project.entity.GarbageBinEntity;
import dev.changuii.project.entity.ReviewEntity;
import dev.changuii.project.entity.ToiletEntity;
import dev.changuii.project.exception.DataNotFoundException;
import dev.changuii.project.exception.InvalidReviewTypeException;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter @Builder
public class ReviewDTO {

    private Long id;
    private String nickname;
    private String password;
    private int rate;
    private List<String> tag;
    private String content;
    private Long toiletId;
    private Long garbageBinId;


    public boolean isType(){
        if(toiletId == null){
            return true;
        }else if(garbageBinId == null){
            return false;
        }else{
            throw new InvalidReviewTypeException();
        }
    }

    public static ReviewEntity toEntity(ReviewDTO reviewDTO){
        return ReviewEntity.builder()
                .nickname(reviewDTO.getNickname())
                .password(reviewDTO.getPassword())
                .rate(reviewDTO.getRate())
                .content(reviewDTO.getContent())
                .tag(reviewDTO.getTag()).build();
    }



}
