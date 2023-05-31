package com.example.animeservice.repository;

import com.example.animeservice.model.ViewedUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("viewedUnitRepository")
public interface ViewedUnitRepository extends JpaRepository<ViewedUnit, Integer> {

    List<ViewedUnit> findViewedUnitByViewedId(int viewedId);

    ViewedUnit findViewedUnitByViewedIdAndAnimeId(int viewedId, int id);
}
