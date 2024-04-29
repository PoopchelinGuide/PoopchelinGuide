package dev.changuii.project.dto;


import dev.changuii.project.entity.GarbageBinEntity;
import dev.changuii.project.entity.ReviewEntity;
import dev.changuii.project.entity.ToiletEntity;
import dev.changuii.project.exception.DataNotFoundException;
import dev.changuii.project.exception.InvalidReviewTypeException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class ReviewDTO {

    private Long id;
    @NotBlank(message = "닉네임을 입력하세요.")
    private String nickname;
    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;
    @NotNull(message = "별점을 선택해주세요.")
    private Double rate;
    private List<String> tag;
    @Size(max =  10, message = "10자 이하로 입력해주세요.")
    private String content;
    private Long toiletId;
    private Long garbageBinId;


    public boolean isType(){
        if(toiletId == null && garbageBinId != null){
            return true;
        }else if(garbageBinId == null && toiletId != null){
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
                .writeDate(LocalDateTime.now())
                .tag(reviewDTO.getTag()).build();
    }



}
