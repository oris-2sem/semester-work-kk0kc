package com.example.animeservice.controllers;

import com.example.animeservice.model.Anime;
import com.example.animeservice.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/json/data")
public class JsonDataController {

    @Autowired
    @Qualifier("animeService")
    private AnimeService animeService;

    @GetMapping("/all/anime")
    public List<Anime> getAllAnime() {
        List<Anime> anime = animeService.findAllAnime();
        return anime;

    }

    @GetMapping("/admin/all/anime")
    public List<Anime> getAllAnimeForAdmin() {
        List<Anime> anime = animeService.findAllAnimeForAdmin();
        return anime;

    }

    @GetMapping("/category/{categoryId}/anime")
    public List<Anime> getAnimeByCategory(@PathVariable("categoryId") int categoryId) {
        List<Anime> anime = animeService.findAnimeByCategoryId(categoryId);
        return anime;

    }
}
