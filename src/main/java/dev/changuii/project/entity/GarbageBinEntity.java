package dev.changuii.project.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @NoArgsConstructor @AllArgsConstructor @Builder
public class GarbageBinEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GARBAGEBIN_ID")
    private long id;

    private String address;
    private String detail;
    private String type;

    @OneToMany(mappedBy = "garbageBin", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<ReviewEntity> reviews = new ArrayList<>();
}
