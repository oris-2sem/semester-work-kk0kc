package com.example.animeservice.service.impl;

import com.example.animeservice.repository.WatchLaterEntityRepository;
import com.example.animeservice.model.WatchLater;
import com.example.animeservice.model.WatchLaterEntity;
import com.example.animeservice.service.WatchLaterEntityService;
import com.example.animeservice.service.WatchLaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("watchLaterEntityService")
public class WatchLaterEntityServiceImpl implements WatchLaterEntityService {

    @Autowired
    private WatchLaterService watchLaterService;

    @Autowired
    private WatchLaterEntityRepository watchLaterEntityRepository;

    @Override
    public WatchLaterEntity findWatchLaterEntityById(int id) {
        return watchLaterEntityRepository.getOne(id);
    }


    @Override
    public boolean saveWatchLaterEntity(WatchLaterEntity watchLaterEntity) {
        watchLaterEntityRepository.saveAndFlush(watchLaterEntity);
        return true;
    }

    @Override
    public boolean updateWatchLaterEntity(WatchLaterEntity watchLaterEntity) {
        watchLaterEntityRepository.saveAndFlush(watchLaterEntity);
        return true;
    }

    @Override
    public boolean deleteWatchLaterEntity(WatchLaterEntity watchLaterEntity) {
        watchLaterEntityRepository.delete(watchLaterEntity);
        return false;
    }

    @Override
    public List<WatchLaterEntity> findAllWatchLaterEntity(int watchLaterId) {
        return watchLaterEntityRepository.findAll();
    }

    @Override
    public List<WatchLaterEntity> findWatchLaterEntity() {
        WatchLater watchLater = watchLaterService.findWatchLater();
        return watchLaterEntityRepository.findWatchLaterEntityByWatchLaterId(watchLater.getId());
    }

    @Override
    public WatchLaterEntity findWatchLaterEntityByWatchLaterIdAndAnimeId(int watchId, int id) {
        return watchLaterEntityRepository.findWatchLaterEntityByWatchLaterIdAndAnimeId(watchId, id);
    }

    @Override
    public WatchLaterEntity findWatchLaterEntityByAnimeId(int id) {
        return watchLaterEntityRepository.getOne(id);
    }
}
