package dev.changuii.project.dao;

import dev.changuii.project.entity.GarbageBinEntity;

public interface GarbageBinDAO {


    public GarbageBinEntity readByIdGarbageBin(Long id);
    void createGarbageBin(GarbageBinEntity garbageBinEntity);
}
