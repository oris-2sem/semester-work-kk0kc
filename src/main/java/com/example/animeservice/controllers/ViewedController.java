package com.example.animeservice.controllers;

import com.example.animeservice.model.*;
import com.example.animeservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/viewed")
public class ViewedController {

    @Autowired
    @Qualifier("viewedUnitService")
    private ViewedUnitService viewedUnitService;

    @Autowired
    @Qualifier("animeService")
    private AnimeService animeService;

    @Autowired
    @Qualifier("viewedService")
    private ViewedService viewedService;

    @Autowired
    @Qualifier("watchLaterService")
    private WatchLaterService watchLaterService;

    @Autowired
    @Qualifier("watchLaterEntityService")
    private WatchLaterEntityService watchLaterEntityService;

    @GetMapping("/show")
    public ModelAndView showViewed(@RequestParam(name = "result", required = false) String result) {
        ModelAndView modelAndView = new ModelAndView("page");

        if (result != null) {
            switch (result) {
                case "updated":
                    modelAndView.addObject("message", "Viewed List has been updated sucessfully");
                    break;

                case "error":
                    modelAndView.addObject("message", "Something went wrong!!");
                    break;
                case "repeat":
                    modelAndView.addObject("message", "This anime already in Viewed List!");
                    break;

                case "added":
                    modelAndView.addObject("message", "Anime has been added sucessfully in Viewed List!");
                    break;

                case "deleted":
                    modelAndView.addObject("message", "Anime has been removed from Viewed List sucessfully");
                    break;
                default:
                    break;
            }
        }

        modelAndView.addObject("title", "User Viewed List");
        modelAndView.addObject("userClickShowViewed", true);
        modelAndView.addObject("viewedUnit", viewedUnitService.findViewedUnit());
        return modelAndView;
    }


    @GetMapping("/{id}/delete")
    public String deleteViewed(@PathVariable int id) {
        ViewedUnit viewedUnit = viewedUnitService.findViewedUnitById(id);
        if (viewedUnit != null) {
            Viewed viewed = viewedService.findViewed();
            viewed.setViewedUnit(viewed.getViewedUnit() - 1);
            viewedService.updateViewed(viewed);
            viewedUnitService.deleteViewedUnit(viewedUnit);
            return "redirect:/viewed/show?result=deleted";
        } else {
            return "redirect:/viewed/show?result=error";
        }
    }

    @GetMapping("/add/{id}/anime")
    public String addViewed(@PathVariable int id) {
        Viewed viewed = viewedService.findViewed();
        WatchLater watchLater = watchLaterService.findWatchLater();
        List<WatchLaterEntity> watchLaterEntityList = watchLaterEntityService.findAllWatchLaterEntity(watchLater.getId());
        ViewedUnit viewedUnit = viewedUnitService.findViewedUnitByViewedIdAndAnimeId(viewed.getId(), id);
        List<ViewedUnit> viewedUnitList = viewedUnitService.findViewedUnit();
        for (ViewedUnit oneViewed : viewedUnitList) {
            if (oneViewed.getAnime().getId() == id) {
                return "redirect:/show/all/anime?result=repeatwatch";
            }
        }
        if (viewedUnit != null) {
            return "";
        } else {

            for (WatchLaterEntity watchLaterEntity : watchLaterEntityList) {
                if (watchLaterEntity.getAnime().getId() == id) {
                    watchLaterEntityService.deleteWatchLaterEntity(watchLaterEntity);
                }
            }
            viewedUnit = new ViewedUnit();

            Anime anime = animeService.findAnimeById(id);
            viewedUnit.setViewedId(viewed.getId());
            viewedUnit.setAnime(anime);
            viewedUnit.setAvailable(true);
            viewedUnitService.saveViewedUnit(viewedUnit);
            viewed.setViewedUnit(viewed.getViewedUnit() + 1);
            viewedService.saveViewed(viewed);
            return "redirect:/viewed/show?result=added";
        }
    }
}
