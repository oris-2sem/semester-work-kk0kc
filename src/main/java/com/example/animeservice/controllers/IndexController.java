package com.example.animeservice.controllers;

import com.example.animeservice.model.Category;
import com.example.animeservice.model.Anime;
import com.example.animeservice.service.CategoryService;
import com.example.animeservice.service.AnimeService;
import com.example.animeservice.util.PasswordResetClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class IndexController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    @Qualifier("passwordResetClient")
    private PasswordResetClient passwordResetClient;

    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;

    @Autowired
    @Qualifier("animeService")
    private AnimeService animeService;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("userClickHome", true);
        modelAndView.addObject("title", "Home");

        modelAndView.addObject("categories", categoryService.findAllCategories());
        modelAndView.addObject("top1anime", animeService.getAnimeWithTopViews().get(0));
        modelAndView.addObject("top2anime", animeService.getAnimeWithTopViews().get(1));
        modelAndView.addObject("top3anime", animeService.getAnimeWithTopViews().get(2));
        modelAndView.addObject("top4anime", animeService.getAnimeWithTopViews().get(3));

        return modelAndView;
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("userClickAbout", true);
        modelAndView.addObject("title", "About Us");

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(name = "error", required = false) String error,
                              @RequestParam(name = "logout", required = false) String logout,
                              @RequestParam(name = "forgot", required = false) String forgot,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("login");
        if (error != null) {
            modelAndView.addObject("message", "Invalid User Name or Password!");
        }
        if (logout != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                new SecurityContextLogoutHandler().logout(request, response, authentication);
            }
            modelAndView.addObject("logout", "User has Successfully Logged out!");
        }
        if (forgot != null) {
           passwordResetClient.sendPasswordResetEmail(httpSession.getAttribute("forgotpassword").toString());
            modelAndView.addObject("forgot", "We sent you email, please change and confirm new password!");
        }
        modelAndView.addObject("title", "Login");

        return modelAndView;
    }

    @GetMapping("/show/all/anime")
    public ModelAndView showAllAnime(@RequestParam(name = "result", required = false) String result) {
        ModelAndView modelAndView = new ModelAndView("page");
        modelAndView.addObject("userClickAllAnime", true);
        modelAndView.addObject("title", "All Films");

        if (result != null) {
            switch (result) {
                case "repeatwatch":
                    modelAndView.addObject("message", "This anime already in Viewed List!");
                    break;
                case "repeatlater":
                modelAndView.addObject("message", "This anime already in Saved List!");
                break;}}
        modelAndView.addObject("categories", categoryService.findAllCategories());

        return modelAndView;

    }

    @GetMapping("/show/category/{id}/anime")
    public ModelAndView showCategoryAnime(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("page");

        Category category = null;
        category = categoryService.findCategoryById(id);

        modelAndView.addObject("userClickCategoryAnime", true);
        modelAndView.addObject("title", category.getName());
        modelAndView.addObject("categories", categoryService.findAllCategories());
        modelAndView.addObject("category", category);

        return modelAndView;
    }

    @GetMapping("/show/{id}/anime")
    public ModelAndView showSingleAnime(@PathVariable("id") int id) throws AnimeNotFoundExceptoion {

        ModelAndView modelAndView = new ModelAndView("page");

        Anime anime = animeService.findAnimeById(id);

        if (anime == null)
            throw new AnimeNotFoundExceptoion();

        anime.setViews(anime.getViews() + 1);
        animeService.updateAnime(anime);
        modelAndView.addObject("title", anime.getName());
        modelAndView.addObject("anime", anime);
        modelAndView.addObject("userClickShowAnime", true);

        return modelAndView;

    }

    @GetMapping("/access-denied")
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.addObject("title", "403 - Access Denied");
        modelAndView.addObject("errorTitle", "Aha! Caught You");
        modelAndView.addObject("errorDescription", "You Are not authorized to Access this Page");
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout=true";
    }
}
