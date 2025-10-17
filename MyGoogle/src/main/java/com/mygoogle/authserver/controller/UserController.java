package com.mygoogle.authserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygoogle.authserver.entity.User;
import com.mygoogle.authserver.repository.UserRepository;

@RestController
@RequestMapping("/mygoogle")
public class UserController {

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        
        repo.save(user);
        return "User registered successfully";
    }
}
