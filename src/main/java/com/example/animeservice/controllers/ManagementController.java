package com.example.animeservice.controllers;

import com.example.animeservice.model.Anime;
import com.example.animeservice.model.Category;
import com.example.animeservice.service.CategoryService;
import com.example.animeservice.service.AnimeService;
import com.example.animeservice.validator.AnimeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManagementController {

    @Qualifier("categoryService")
    @Autowired
    private CategoryService categoryService;

    @Qualifier("animeService")
    @Autowired
    private AnimeService animeService;

    @GetMapping(value = "/anime")
    public ModelAndView showManageAnime(@RequestParam(name = "operation", required = false) String operation) {

        ModelAndView modelAndView = new ModelAndView("page");

        modelAndView.addObject("userClickManageAnime", true);
        modelAndView.addObject("title", "Manage anime");

        Anime nAnime = new Anime();
        nAnime.setActive(true);
        modelAndView.addObject("anime", nAnime);
        if (operation != null && operation.equals("anime")) {
            modelAndView.addObject("message", "Anime Added Sucessfully!");
        } else if (operation != null && operation.equals("category")) {
            modelAndView.addObject("message", "Category Added Sucessfully!");
        } else if (operation != null && operation.equals("updated")) {
            modelAndView.addObject("message", "Anime Updated Sucessfully!");
        }

        return modelAndView;
    }

    @PostMapping(value = "/anime")
    public String handleAnimSubmission(@Valid @ModelAttribute("anime") Anime mAnime,
                                       BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file,
                                       HttpServletRequest request) {

        if (mAnime.getId() == 0) {

            new AnimeValidator().validate(mAnime, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("userClickManageAnime", true);
                model.addAttribute("title", "Manage Anime");
                model.addAttribute("message", "Validation failed for Anime Submission!");
                return "page";
            }

            try {
                fileSaveInFolder(mAnime, file, request);
                animeService.saveAnime(mAnime);
                return "redirect:/manage/anime?operation=anime";
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("userClickManageAnime", true);
                model.addAttribute("title", "Manage Anime");
                model.addAttribute("message", e.getMessage());
                return "page";
            }
        } else {
            if (!mAnime.getFile().getOriginalFilename().equals("")) {
                new AnimeValidator().validate(mAnime, bindingResult);
                if (bindingResult.hasErrors()) {
                    model.addAttribute("userClickManageAnime", true);
                    model.addAttribute("title", "Manage Anime");
                    model.addAttribute("message", "Validation failed for Anime Submission!");
                    return "page";
                }

                try {

                    fileSaveInFolder(mAnime, file, request);
                    animeService.updateAnime(mAnime);
                    return "redirect:/manage/anime?operation=updated";
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("userClickManageAnime", true);
                    model.addAttribute("title", "Manage Anime");
                    model.addAttribute("message", e.getMessage());
                    return "page";
                }

            } else {
                animeService.updateAnime(mAnime);
                return "redirect:/manage/anime?operation=updated";
            }
        }
    }

    private void fileSaveInFolder(Anime mAnime, MultipartFile file, HttpServletRequest request) throws IOException {
        byte[] bytes = file.getBytes();
        String p = request.getSession().getServletContext().getRealPath("/static/images/");
        System.out.println(p);
        Path path = Paths.get(p + mAnime.getCode() + ".jpg");
        Files.write(path, bytes);
    }

    @PostMapping("/anime/{id}/activation")
    @ResponseBody
    public String handleAnimeActivation(@PathVariable("id") int id) {

        Anime anime = animeService.findAnimeByIdForAdmin(id);
        boolean isActive = anime.isActive();

        anime.setActive(!isActive);

        animeService.updateAnime(anime);

        return (isActive) ? "You have Successfully deactivated the Anime with Id : " + anime.getId()
                : "You have Successfully activated the Anime with Id : " + anime.getId();
    }

    @GetMapping("{id}/anime")
    public ModelAndView showEditAnime(@PathVariable("id") int id) {

        ModelAndView modelAndView = new ModelAndView("page");

        modelAndView.addObject("userClickManageAnime", true);
        modelAndView.addObject("title", "Manage Anime");

        Anime nAnime = animeService.findAnimeByIdForAdmin(id);

        modelAndView.addObject("anime", nAnime);

        return modelAndView;
    }

    @PostMapping("/category")
    public String handleCategorySubmission(@ModelAttribute("category") Category category) {

        categoryService.saveCategory(category);

        return "redirect:/manage/anime?operation=category";
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        List<Category> categories = categoryService.findAllCategories();
        return categories;
    }

    @ModelAttribute("category")
    public Category getCategory() {
        return new Category();
    }

}
