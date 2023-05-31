package com.example.animeservice.validator;

import com.example.animeservice.model.Anime;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AnimeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Anime.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Anime anime = (Anime) target;

        if (anime.getFile() == null || anime.getFile().getOriginalFilename().equals("")) {
            errors.rejectValue("file", null, "Please select an image file to upload!");
            return;
        }
        if (!(anime.getFile().getContentType().equals("image/jpeg")
                || anime.getFile().getContentType().equals("image/png"))) {
            errors.rejectValue("file", null, "Please use only an image file to upload!");
            return;

        }
    }
}
