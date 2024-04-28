package dev.changuii.project.service;

import dev.changuii.project.dto.GarbageBinDTO;

import java.util.List;

public interface GarbageBinService {
    List<GarbageBinDTO> readAllByBoxRange(Double x1, Double x2, Double y1, Double y2);
}
