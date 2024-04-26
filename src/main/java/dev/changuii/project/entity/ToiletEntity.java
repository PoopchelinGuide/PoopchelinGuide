package dev.changuii.project.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @NoArgsConstructor @AllArgsConstructor @Builder
public class ToiletEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOILET_ID")
    private long id;

    private String name;

    private double coordinateX;

    private double coordinateY;

    @OneToMany(mappedBy = "toilet", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<ReviewEntity> reviews = new ArrayList<>();


}
