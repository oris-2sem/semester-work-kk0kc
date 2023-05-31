package com.example.animeservice.service;

import com.example.animeservice.model.ViewedUnit;

import java.util.List;

public interface ViewedUnitService {

    public ViewedUnit findViewedUnitById(int id);
    public ViewedUnit findViewedUnitByAnimeId(int id);

    public boolean saveViewedUnit(ViewedUnit viewedUnit);

    public boolean updateViewedUnit(ViewedUnit viewedUnit);

    public boolean deleteViewedUnit(ViewedUnit viewedUnit);

    public List<ViewedUnit> findAllViewedUnit(int viewedId);

    public List<ViewedUnit> findViewedUnit();

    ViewedUnit findViewedUnitByViewedIdAndAnimeId(int viewedId, int id);

}
