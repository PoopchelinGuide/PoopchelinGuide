package dev.changuii.project.dao.impl;

import dev.changuii.project.dao.ToiletDAO;
import dev.changuii.project.entity.ToiletEntity;
import dev.changuii.project.exception.DataNotFoundException;
import dev.changuii.project.repository.GarbageBinRepository;
import dev.changuii.project.repository.ToiletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ToiletDAOImpl implements ToiletDAO {

    private ToiletRepository toiletRepository;

    public ToiletDAOImpl(
            @Autowired ToiletRepository toiletRepository
    ){
        this.toiletRepository = toiletRepository;
    }


    @Override
    public void createToilet(ToiletEntity toilet) {
        this.toiletRepository.save(toilet);
    }

    @Override
    public ToiletEntity readByIdToilet(Long id) {
        return this.toiletRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }
}
