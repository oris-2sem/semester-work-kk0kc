package com.example.animeservice.service;

import com.example.animeservice.model.Anime;

import java.util.List;

public interface AnimeService {

    String saveAnime (Anime anime);

    List<Anime> findAllAnime();

    List<Anime> findAllAnimeForAdmin();

    Anime findAnimeById(Integer id);

    String deleteAnime(Integer id);

    String updateAnime(Anime anime);

    List<Anime> findAnimeByCategoryId(Integer categoryId);

    Anime findAnimeByIdForAdmin(int id);
    public List<Anime> getAnimeWithTopViews();


}
