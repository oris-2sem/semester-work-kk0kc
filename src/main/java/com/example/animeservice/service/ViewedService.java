package com.example.animeservice.service;

import com.example.animeservice.model.Viewed;

public interface ViewedService {

    boolean saveViewed(Viewed viewed);

    boolean updateViewed(Viewed viewed);

    Viewed findViewed();

}
