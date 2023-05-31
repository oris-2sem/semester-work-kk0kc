package com.example.animeservice.service.impl;

import com.example.animeservice.model.Anime;
import com.example.animeservice.repository.AnimeCustomRepository;
import com.example.animeservice.repository.AnimeRepository;
import com.example.animeservice.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("animeService")
public class AnimeServiceImpl implements AnimeService {

    @Qualifier("animeRepository")
    @Autowired
    private AnimeRepository animeRepository;

    @Qualifier("animeCustomRepository")
    @Autowired
    private AnimeCustomRepository animeCustomRepository;

    @Override
    public String saveAnime(Anime anime) {
        // TODO Auto-generated method stub
        animeRepository.saveAndFlush(anime);
        return "Anime Added Sucessfully";
    }

    @Override
    public List<Anime> findAllAnime() {
        try {
            List<Anime> anime = new ArrayList<>();
            List<Anime> animeCopy = new ArrayList<>();
            animeRepository.findAll().forEach(anime::add);
            animeCopy.addAll(anime);
            for (Anime p : anime) {
                if (!p.isActive()) {
                    animeCopy.remove(p);
                }
            }
            return animeCopy;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Anime findAnimeById(Integer id) {
        // TODO Auto-generated method stub
        Anime anime = animeRepository.getOne(id);
        if (!anime.isActive())
            anime = null;

        return anime;
    }

    @Override
    public String deleteAnime(Integer id) {

        Anime anime = animeRepository.getOne(id);

        anime.setActive(false);
        animeRepository.saveAndFlush(anime);

        return "Anime Deleted Sucessfully";
    }

    @Override
    public String updateAnime(Anime anime) {
        animeRepository.saveAndFlush(anime);
        return "Anime Updated Sucessfully";
    }

    @Override
    public List<Anime> findAnimeByCategoryId(Integer categoryId) {
        List<Anime> anime = new ArrayList<>();
        List<Anime> animeCopy = new ArrayList<>();
        animeRepository.findAnimeByCategoryId(categoryId).forEach(anime::add);
        animeCopy.addAll(anime);
        for (Anime p : anime) {
            if (!p.isActive()) {
                animeCopy.remove(p);
            }
        }
        return animeCopy;
    }

    @Override
    public List<Anime> findAllAnimeForAdmin() {
        List<Anime> anime = new ArrayList<>();
        animeRepository.findAll().forEach(anime::add);
        return anime;
    }

    @Override
    public Anime findAnimeByIdForAdmin(int id) {
        Anime anime = animeRepository.getOne(id);
        return anime;
    }

    @Override
    public List<Anime> getAnimeWithTopViews() {
        return animeRepository.getAnimeWithTopViews();
    }


}
