package com.example.animeservice.controllers;

import java.io.Serializable;

public class AnimeNotFoundExceptoion extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    public AnimeNotFoundExceptoion(String message) {
        this.message = System.currentTimeMillis() + " : " + message;
    }

    public AnimeNotFoundExceptoion() {
        this("Anime Not Available!");
    }

    public String getMessage() {
        return message;
    }

}
