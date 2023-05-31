package com.example.animeservice.service.impl;

import com.example.animeservice.model.UserModel;
import com.example.animeservice.model.WatchLater;
import com.example.animeservice.repository.WatchLaterRepository;
import com.example.animeservice.service.WatchLaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("watchLaterService")
public class WatchLaterServiceImpl implements WatchLaterService {

    @Qualifier("watchLaterRepository")
    @Autowired
    private WatchLaterRepository watchLaterRepository;

    @Autowired
    private HttpSession httpSession;

    @Override
    public boolean saveWatchLater(WatchLater watchLater) {
        watchLaterRepository.saveAndFlush(watchLater);
        return true;
    }

    @Override
    public boolean updateWatchLater(WatchLater watchLater) {
        watchLaterRepository.saveAndFlush(watchLater);
        return true;
    }

    @Override
    public WatchLater findWatchLater() {
        return ((UserModel) httpSession.getAttribute("userModel")).getWatchLater();
    }
}
