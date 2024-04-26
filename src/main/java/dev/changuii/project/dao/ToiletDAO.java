package dev.changuii.project.dao;

import dev.changuii.project.entity.ToiletEntity;

public interface ToiletDAO {

    public ToiletEntity readByIdToilet(Long id);
}
