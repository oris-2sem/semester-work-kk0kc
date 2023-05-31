package com.example.animeservice.repository;

import com.example.animeservice.model.Anime;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class AnimeCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Anime getAnimeWithTopViews() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Anime> criteriaQuery = criteriaBuilder.createQuery(Anime.class);
        Root<Anime> root = criteriaQuery.from(Anime.class);
        criteriaQuery.select(root)
                .orderBy(criteriaBuilder.desc(root.get("views")));

        TypedQuery<Anime> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setMaxResults(1);

        return typedQuery.getSingleResult();
    }
}