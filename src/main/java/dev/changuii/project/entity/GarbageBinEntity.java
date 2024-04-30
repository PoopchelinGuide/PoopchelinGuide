package dev.changuii.project.entity;


import dev.changuii.project.dto.GarbageBinDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @NoArgsConstructor @AllArgsConstructor @Builder @Getter
public class GarbageBinEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GARBAGEBIN_ID")
    private long id;

    private String address;
    private double coordinateX;
    private double coordinateY;
    private String name;
    private String type;


    @Builder.Default
    @OneToMany(mappedBy = "garbageBin", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<ReviewEntity> reviews = new ArrayList<>();

    public static GarbageBinDTO toDTO(GarbageBinEntity garbageBinEntity){
        return GarbageBinDTO.builder()
                .id(garbageBinEntity.getId())
                .address(garbageBinEntity.getAddress())
                .coordinateX(garbageBinEntity.getCoordinateX())
                .coordinateY(garbageBinEntity.getCoordinateY())
                .name(garbageBinEntity.getName())
                .type(garbageBinEntity.getType())
                .build();
    }

    public static List<GarbageBinDTO> doDTOList(List<GarbageBinEntity> garbageBinEntities){
        List<GarbageBinDTO> garbageBinDTOList = new ArrayList<>();
        garbageBinEntities.forEach(garbageBinEntity -> garbageBinDTOList.add(toDTO(garbageBinEntity)));
        return garbageBinDTOList;
    }
}
