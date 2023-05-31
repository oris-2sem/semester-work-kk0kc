package com.example.animeservice.repository;

import com.example.animeservice.model.WatchLater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("watchLaterRepository")
public interface WatchLaterRepository extends JpaRepository<WatchLater, Integer> {

}
