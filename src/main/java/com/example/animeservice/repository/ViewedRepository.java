package com.example.animeservice.repository;

import com.example.animeservice.model.Viewed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("viewedRepository")
public interface ViewedRepository extends JpaRepository<Viewed, Integer> {

}
