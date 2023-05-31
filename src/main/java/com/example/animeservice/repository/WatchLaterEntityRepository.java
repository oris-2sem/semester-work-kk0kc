package com.example.animeservice.repository;

import com.example.animeservice.model.WatchLaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("watchLaterEntityRepository")
public interface WatchLaterEntityRepository extends JpaRepository<WatchLaterEntity, Integer> {
    List<WatchLaterEntity> findWatchLaterEntityByWatchLaterId(int watchLaterId);
    WatchLaterEntity findWatchLaterEntityByWatchLaterIdAndAnimeId(int watchLaterId, int id);
}
