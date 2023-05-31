package com.example.animeservice.service;

import com.example.animeservice.model.WatchLaterEntity;

import java.util.List;

public interface WatchLaterEntityService {

    public WatchLaterEntity findWatchLaterEntityById(int id);
    public WatchLaterEntity findWatchLaterEntityByAnimeId(int id);

    public boolean saveWatchLaterEntity(WatchLaterEntity watchLaterEntity);

    public boolean updateWatchLaterEntity(WatchLaterEntity watchLaterEntity);

    public boolean deleteWatchLaterEntity(WatchLaterEntity watchLaterEntity);

    public List<WatchLaterEntity> findAllWatchLaterEntity(int watchLaterId);

    public List<WatchLaterEntity> findWatchLaterEntity();

    WatchLaterEntity findWatchLaterEntityByWatchLaterIdAndAnimeId(int watchLaterId, int id);

}
