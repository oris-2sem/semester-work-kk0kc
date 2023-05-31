package com.example.animeservice.handler;


import com.example.animeservice.model.Viewed;
import com.example.animeservice.service.UserService;
import com.example.animeservice.model.RegisterModel;
import com.example.animeservice.model.User;
import com.example.animeservice.model.WatchLater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

@Component
public class RegisterHandler {

    @Qualifier("userService")
    @Autowired
    private UserService userService;

    public RegisterModel init() {

        return new RegisterModel();

    }

    public void addUser(RegisterModel registerModel, User user) {
        registerModel.setUser(user);
    }

    public String saveAll(RegisterModel registerModel) {
        String transitionValue = "success";

        User user = registerModel.getUser();
        if (user.getRole().equalsIgnoreCase("USER")) {
            Viewed viewed = new Viewed();
            WatchLater watchLater = new WatchLater();
            viewed.setUser(user);
            watchLater.setUser(user);
            user.setViewed(viewed);
            user.setWatchLater(watchLater);
        }
        userService.saveUser(user);

        return transitionValue;
    }

    public String validateUser(User user, MessageContext messageContext) {

        String transitionValue = "success";
        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            messageContext.addMessage(new MessageBuilder().error().source("confirmPassword")
                    .defaultText("Password does not match the confirm Password").build());
            transitionValue = "failure";
        }

        if (userService.findUserByEmail(user.getEmail()) != null) {
            messageContext.addMessage(
                    new MessageBuilder().error().source("email").defaultText("Email is already in use").build());
            transitionValue = "failure";
        }

        return transitionValue;
    }

}
