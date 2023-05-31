package com.example.animeservice.repository;

import com.example.animeservice.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("animeRepository")
public interface AnimeRepository extends JpaRepository<Anime, Integer> {

    List<Anime> findAnimeByCategoryId(Integer categoryId);
    @Query(nativeQuery = true, value = "SELECT * FROM anime ORDER BY views DESC LIMIT 4;")
    public List<Anime> getAnimeWithTopViews();

}
