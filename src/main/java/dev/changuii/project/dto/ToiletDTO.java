package dev.changuii.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ToiletDTO {

    private long id;
    private String name;
    private double coordinateX;
    private double coordinateY;

    @Builder.Default
    private List<ReviewDTO> reviews = new ArrayList<>();


}
