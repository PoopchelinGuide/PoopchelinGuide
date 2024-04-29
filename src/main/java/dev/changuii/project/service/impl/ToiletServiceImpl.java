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

    private final ToiletDAO toiletDAO;

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

    @Override
    public ToiletDTO findNearestToilet(Double x1, Double x2, Double y1, Double y2, Double x3, Double y3) {
        return ToiletFinder(x3, y3, ToiletEntity.toDTOList(this.toiletDAO.readAllByBoxRange(x1, x2, y1, y2)));
    }


    public ToiletDTO ToiletFinder(Double x3, Double y3, List<ToiletDTO> toiletDTOS){ //가장 근처 Toilet 찾기
        ToiletDTO nearestToilet = null;

        double nearestDistance = Double.MAX_VALUE;

        for(ToiletDTO toiletDTO : toiletDTOS){
            double distance = calculateDistance(x3, y3, toiletDTO.getCoordinateX(), toiletDTO.getCoordinateY());

            if(distance < nearestDistance){
                nearestDistance = distance;
                nearestToilet = toiletDTO;
            }
        }
        return nearestToilet;
    }

    public double calculateDistance(Double x3, Double y3, Double x2, Double y2) { //점과 점 사이의 거리계산

        Double x1d = Math.floor(x3);
        double x1m = Math.floor((x3 - x1d)*60);
        Double x1s = Math.floor(((x3 - x1d) * 60 - x1m) * 60 * 100) / 100;

        Double y1d = Math.floor(y3);
        double y1m = Math.floor((y3 - y1d)*60);
        Double y1s = Math.floor(((y3 - y1d) * 60 - y1m) * 60 * 100) / 100;

        Double x2d = Math.floor(x2);
        double x2m = Math.floor((x2 - x2d)*60);
        Double x2s = Math.floor(((x2 - x2d) * 60 - x2m) * 60 * 100) / 100;

        Double y2d = Math.floor(y2);
        double y2m = Math.floor((y2 - y2d)*60);
        Double y2s = Math.floor(((y2 - y2d) * 60 - y2m) * 60 * 100) / 100;

        //Double C = Math.cos(Math.toRadians(x1d))*(2*Math.PI*earth/360);

        return Math.sqrt(Math.pow((x1d-x2d)*88.9036+(x1m-x2m)*1.4817+(x1s-x2s)*0.0246,2)
                + Math.pow((y1d-y2d)*111.3194+(y1m-y2m)*1.8553+(y1s-y2s)*0.0309,2));
    }
}
