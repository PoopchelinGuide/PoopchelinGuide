package dev.changuii.project.service.impl;

import dev.changuii.project.dao.ToiletDAO;
import dev.changuii.project.dto.ToiletDTO;
import dev.changuii.project.entity.ToiletEntity;
import dev.changuii.project.service.ToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToiletServiceImpl implements ToiletService {

    private ToiletDAO toiletDAO;

    public ToiletServiceImpl(
            @Autowired ToiletDAO toiletDAO
    ){
        this.toiletDAO = toiletDAO;
    }

    @Override
    public List<ToiletDTO> readAllByBoxRange(Double x1, Double x2, Double y1, Double y2) {
        return ToiletEntity
                .toDTOList(this.toiletDAO.readAllByBoxRange(x1, x2, y1, y2));
    }
}
