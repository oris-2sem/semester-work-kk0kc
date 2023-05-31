package com.example.animeservice.controllers;

import com.example.animeservice.model.Anime;
import com.example.animeservice.model.WatchLater;
import com.example.animeservice.model.WatchLaterEntity;
import com.example.animeservice.service.AnimeService;
import com.example.animeservice.service.WatchLaterEntityService;
import com.example.animeservice.service.WatchLaterService;
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
@RequestMapping("/watchlater")
public class WatchLaterController {

    @Autowired
    @Qualifier("watchLaterEntityService")
    private WatchLaterEntityService watchLaterEntityService;

    @Autowired
    @Qualifier("animeService")
    private AnimeService animeService;

    @Autowired
    @Qualifier("watchLaterService")
    private WatchLaterService watchLaterService;

    @GetMapping("/show")
    public ModelAndView showWatchLater(@RequestParam(name = "result", required = false) String result) {
        ModelAndView modelAndView = new ModelAndView("page");

        if (result != null) {
            switch (result) {
                case "updated":
                    modelAndView.addObject("message", "Watch Later has been updated sucessfully");
                    break;

                case "error":
                    modelAndView.addObject("message", "Something went wrong!!");
                    break;
                case "repeat":
                    modelAndView.addObject("message", "This anime already in Watch Later!");
                    break;

                case "added":
                    modelAndView.addObject("message", "Watch Later has been added sucessfully!");
                    break;

                case "deleted":
                    modelAndView.addObject("message", "Watch Later has been removed sucessfully");
                    break;
                default:
                    break;
            }
        }

        modelAndView.addObject("title", "User Watch Later");
        modelAndView.addObject("userClickShowWatchLater", true);
        modelAndView.addObject("watchLaterEntity", watchLaterEntityService.findWatchLaterEntity());
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public String deleteWatchLater(@PathVariable int id) {
        WatchLaterEntity watchLaterEntity = watchLaterEntityService.findWatchLaterEntityById(id);
        if (watchLaterEntity != null) {
            WatchLater watchLater = watchLaterService.findWatchLater();
            watchLater.setWatchLaterEntity(watchLater.getWatchLaterEntity() - 1);
            watchLaterService.updateWatchLater(watchLater);
            watchLaterEntityService.deleteWatchLaterEntity(watchLaterEntity);
            return "redirect:/watchlater/show?result=deleted";
        } else {
            return "redirect:/watchlater/show?result=error";
        }
    }

    @GetMapping("/add/{id}/anime")
    public String addWatchLater(@PathVariable int id) {
        WatchLater watchLater = watchLaterService.findWatchLater();
        WatchLaterEntity watchLaterEntity = watchLaterEntityService.findWatchLaterEntityByWatchLaterIdAndAnimeId(watchLater.getId(), id);
        List<WatchLaterEntity> watchLaterEntityList = watchLaterEntityService.findWatchLaterEntity();
        for (WatchLaterEntity oneWatchLater: watchLaterEntityList) {
            if (oneWatchLater.getAnime().getId() == id) {
                return "redirect:/show/all/anime?result=repeatlater";
            }
        }

        if (watchLaterEntity != null) {
            return "";
        } else {

            watchLaterEntity = new WatchLaterEntity();

            Anime anime = animeService.findAnimeById(id);
            watchLaterEntity.setWatchLaterId(watchLater.getId());
            watchLaterEntity.setAnime(anime);
            watchLaterEntity.setAvailable(true);
            watchLaterEntityService.saveWatchLaterEntity(watchLaterEntity);
            watchLater.setWatchLaterEntity(watchLater.getWatchLaterEntity() + 1);
            watchLaterService.saveWatchLater(watchLater);
            return "redirect:/watchlater/show?result=added";
        }
    }
}
