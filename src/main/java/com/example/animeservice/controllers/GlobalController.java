package com.example.animeservice.controllers;

import com.example.animeservice.model.User;
import com.example.animeservice.model.UserModel;
import com.example.animeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserService userService;

    private UserModel userModel = null;

    @ModelAttribute("userModel")
    public UserModel getUserModel() {

        if (httpSession.getAttribute("userModel") == null) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(authentication.getName());
            if (user != null) {
                userModel = new UserModel();
                userModel.setId(user.getId());
                userModel.setEmail(user.getEmail());
                userModel.setRole(user.getRole());
                userModel.setFullName(user.getFirstName());
                if (userModel.getRole().equalsIgnoreCase("USER")) {

                    userModel.setViewed(user.getViewed());
                    userModel.setWatchLater(user.getWatchLater());
                }
                httpSession.setAttribute("userModel", userModel);
            }

        }

        return (UserModel) httpSession.getAttribute("userModel");
    }
}
