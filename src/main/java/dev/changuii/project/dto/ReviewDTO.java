package dev.changuii.project.dto;


import dev.changuii.project.entity.GarbageBinEntity;
import dev.changuii.project.entity.ReviewEntity;
import dev.changuii.project.entity.ToiletEntity;
import dev.changuii.project.exception.DataNotFoundException;
import dev.changuii.project.exception.InvalidReviewTypeException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter @Builder
public class ReviewDTO {

    private Long id;
    @NotBlank(message = "닉네임을 입력하세요.")
    private String nickname;
    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;
    private int rate;
    private List<String> tag;
    @Size(max =  10, message = "10자 이하로 입력해주세요.")
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
