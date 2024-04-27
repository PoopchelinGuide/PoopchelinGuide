package dev.changuii.project.entity;


import dev.changuii.project.dto.ToiletDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity @NoArgsConstructor @AllArgsConstructor @Builder
public class ToiletEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOILET_ID")
    private Long id;

    private String name;

    private Double coordinateX;

    private Double coordinateY;

    @OneToMany(mappedBy = "toilet", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<ReviewEntity> reviews = new ArrayList<>();


    public static ToiletDTO toDTO(ToiletEntity toilet){
        return ToiletDTO.builder()
                .id(toilet.getId())
                .name(toilet.getName())
                .coordinateX(toilet.getCoordinateX())
                .coordinateY(toilet.getCoordinateY()).build();

    }

    public static List<ToiletDTO> toDTOList(List<ToiletEntity> toilets){
        List<ToiletDTO> toiletDTOS = new ArrayList<>();
        toilets.forEach(toilet -> toiletDTOS.add(toDTO(toilet)));
        return toiletDTOS;
    }

}
