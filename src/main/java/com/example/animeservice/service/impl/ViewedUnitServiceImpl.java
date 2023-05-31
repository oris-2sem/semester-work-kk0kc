package com.example.animeservice.service.impl;

import com.example.animeservice.model.ViewedUnit;
import com.example.animeservice.repository.ViewedUnitRepository;
import com.example.animeservice.model.Viewed;
import com.example.animeservice.service.ViewedUnitService;
import com.example.animeservice.service.ViewedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("viewedUnitService")
public class ViewedUnitServiceImpl implements ViewedUnitService {

    @Autowired
    private ViewedService viewedService;

    @Autowired
    private ViewedUnitRepository viewedUnitRepository;

    @Override
    public ViewedUnit findViewedUnitById(int id) {
        return viewedUnitRepository.getOne(id);
    }


    @Override
    public boolean saveViewedUnit(ViewedUnit viewedUnit) {
        viewedUnitRepository.saveAndFlush(viewedUnit);
        return true;
    }

    @Override
    public boolean updateViewedUnit(ViewedUnit viewedUnit) {
        viewedUnitRepository.saveAndFlush(viewedUnit);
        return true;
    }

    @Override
    public boolean deleteViewedUnit(ViewedUnit viewedUnit) {
        viewedUnitRepository.delete(viewedUnit);
        return false;
    }

    @Override
    public List<ViewedUnit> findAllViewedUnit(int viewedId) {
        return viewedUnitRepository.findAll();
    }


    @Override
    public List<ViewedUnit> findViewedUnit() {
        Viewed viewed = viewedService.findViewed();
        return viewedUnitRepository.findViewedUnitByViewedId(viewed.getId());
    }

    @Override
    public ViewedUnit findViewedUnitByViewedIdAndAnimeId(int viewedId, int id) {
        return viewedUnitRepository.findViewedUnitByViewedIdAndAnimeId(viewedId, id);
    }

    @Override
    public ViewedUnit findViewedUnitByAnimeId(int id) {
        return viewedUnitRepository.getOne(id);
    }

}
