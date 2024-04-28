package dev.changuii.project.service.impl;

import dev.changuii.project.dao.GarbageBinDAO;
import dev.changuii.project.dto.GarbageBinDTO;
import dev.changuii.project.entity.GarbageBinEntity;
import dev.changuii.project.service.GarbageBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarbageBinServiceImpl implements GarbageBinService {
    private final GarbageBinDAO garbageBinDAO;

    public GarbageBinServiceImpl(
            @Autowired GarbageBinDAO garbageBinDAO)
    {
        this.garbageBinDAO = garbageBinDAO;
    }

    @Override
    public List<GarbageBinDTO> readAllByBoxRange(Double x1, Double x2, Double y1, Double y2) {
        return GarbageBinEntity.doDTOList(this.garbageBinDAO.readAllByBoxRange(x1,x2,y1,y2));
    }
}
