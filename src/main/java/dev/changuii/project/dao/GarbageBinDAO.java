package dev.changuii.project.dao;

import dev.changuii.project.entity.GarbageBinEntity;

import java.util.List;

public interface GarbageBinDAO {


    public GarbageBinEntity readByIdGarbageBin(Long id);
    void createGarbageBin(GarbageBinEntity garbageBinEntity);
    List<GarbageBinEntity> readAllByBoxRange(Double x1,Double x2,Double x3,Double x4);
}
