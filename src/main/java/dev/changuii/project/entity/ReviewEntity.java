package dev.changuii.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Builder @Getter @AllArgsConstructor @NoArgsConstructor
public class ReviewEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nickname;

    private String password;

    private int rate;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "TOILET_ID")
    private ToiletEntity toilet;

    @ManyToOne
    @JoinColumn(name = "GARBAGEBIN_ID")
    private GarbageBinEntity garbageBin;

}
