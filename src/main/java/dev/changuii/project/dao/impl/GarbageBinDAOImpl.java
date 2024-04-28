package dev.changuii.project.dao.impl;

import dev.changuii.project.dao.GarbageBinDAO;
import dev.changuii.project.entity.GarbageBinEntity;
import dev.changuii.project.exception.DataNotFoundException;
import dev.changuii.project.repository.GarbageBinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GarbageBinDAOImpl implements GarbageBinDAO {

    private final GarbageBinRepository garbageBinRepository;

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
    public List<GarbageBinEntity> readAllByBoxRange(Double x1, Double x2, Double y1, Double y2) {
        return this.garbageBinRepository.findAllByCoordinateXBetweenAndCoordinateYBetween(x1,x2,y1,y2);
    }

    @Override
    public GarbageBinEntity readByIdGarbageBin(Long id) {
        return this.garbageBinRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }

}
