package dev.changuii.project.dao;

import dev.changuii.project.entity.ToiletEntity;

import java.util.List;

public interface ToiletDAO {

    public void createToilet(ToiletEntity toilet);
    public ToiletEntity readByIdToilet(Long id);
    public List<ToiletEntity> readAllByBoxRange(Double x1, Double x2, Double y1, Double y2);
}
