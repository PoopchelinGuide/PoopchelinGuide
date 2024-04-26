package dev.changuii.project.entity;

import dev.changuii.project.dto.GarbageBinDTO;
import dev.changuii.project.dto.ReviewDTO;
import dev.changuii.project.dto.ToiletDTO;
import dev.changuii.project.dto.response.ResponseReviewDTO;
import dev.changuii.project.exception.DataNotFoundException;
import dev.changuii.project.exception.InvalidReviewTypeException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @Builder @Getter
@AllArgsConstructor @NoArgsConstructor
public class ReviewEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nickname;

    private String password;

    private int rate;

    @Lob
    private String content;

    @ElementCollection
    private List<String> tag = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "TOILET_ID")
    private ToiletEntity toilet;

    @ManyToOne
    @JoinColumn(name = "GARBAGEBIN_ID")
    private GarbageBinEntity garbageBin;

    // Review가 화장실인지 쓰레기통인지 체크하여 넣는다.
    public ReviewEntity setType(ToiletEntity toilet, GarbageBinEntity garbageBin){
        if(toilet == null){
            this.garbageBin = garbageBin;
        }
        else if(garbageBin == null){
            this.toilet = toilet;
        }
        else{
            throw new InvalidReviewTypeException();
        }
        return this;
    }


    public static ResponseReviewDTO toResponseDTO(ReviewEntity reviewEntity){
        return ResponseReviewDTO.builder()
                .id(reviewEntity.getId())
                .nickname(reviewEntity.getNickname())
                .rate(reviewEntity.getRate())
                .tag(reviewEntity.getTag())
                .content(reviewEntity.getContent()).build();

        // todo : toilet, garbagebin 도 넣어야함
    }

    public ReviewEntity updateReview(ReviewDTO after){
        return ReviewEntity.builder()
                // 수정 불가
                .id(this.getId())
                .nickname(this.getNickname())
                .password(this.getPassword())
                .rate(this.getRate())

                // 수정 가능
                .tag(after.getTag())
                .content(after.getContent())

                .build();

    }

}
