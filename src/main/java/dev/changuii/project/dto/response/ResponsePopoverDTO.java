package dev.changuii.project.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ResponsePopoverDTO {

    String name;
    List<String> Tag;
    Double rate;
    List<ResponseReviewDTO> recentReview;

}
