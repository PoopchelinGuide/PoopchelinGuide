package dev.changuii.project.dao.impl;

import dev.changuii.project.dao.GarbageBinDAO;
import dev.changuii.project.entity.GarbageBinEntity;
import dev.changuii.project.exception.DataNotFoundException;
import dev.changuii.project.repository.GarbageBinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GarbageBinDAOImpl implements GarbageBinDAO {

    private GarbageBinRepository garbageBinRepository;

    public GarbageBinDAOImpl(
            @Autowired GarbageBinRepository garbageBinRepository
    ){
        this.garbageBinRepository = garbageBinRepository;
    }

    @Override
    public void createGarbageBin(GarbageBinEntity garbageBinEntity){
        this.garbageBinRepository.save(garbageBinEntity);
    }
    @Override
    public GarbageBinEntity readByIdGarbageBin(Long id) {
        return this.garbageBinRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }
}
