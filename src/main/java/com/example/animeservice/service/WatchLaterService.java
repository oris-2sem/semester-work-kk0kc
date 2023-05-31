package com.example.animeservice.service;

import com.example.animeservice.model.WatchLater;

public interface WatchLaterService {

    boolean saveWatchLater(WatchLater watchLater);

    boolean updateWatchLater(WatchLater watchLater);

    WatchLater findWatchLater();

}
