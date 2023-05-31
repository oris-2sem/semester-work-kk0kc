package com.example.animeservice.service.impl;

import com.example.animeservice.model.Viewed;
import com.example.animeservice.repository.ViewedRepository;
import com.example.animeservice.model.UserModel;
import com.example.animeservice.service.ViewedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("viewedService")
public class ViewedServiceImpl implements ViewedService {

    @Qualifier("viewedRepository")
    @Autowired
    private ViewedRepository viewedRepository;

    @Autowired
    private HttpSession httpSession;

    @Override
    public boolean saveViewed(Viewed viewed) {
        viewedRepository.saveAndFlush(viewed);
        return true;
    }

    @Override
    public boolean updateViewed(Viewed viewed) {
        viewedRepository.saveAndFlush(viewed);
        return true;
    }

    @Override
    public Viewed findViewed() {
        return ((UserModel) httpSession.getAttribute("userModel")).getViewed();
    }

}
