package com.example.animeservice.service;

import com.example.animeservice.model.User;

public interface UserService {

    boolean saveUser(User user);

    User findUserByEmail(String email);

}
