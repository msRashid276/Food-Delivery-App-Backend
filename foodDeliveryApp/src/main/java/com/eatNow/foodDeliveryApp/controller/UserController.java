package com.eatNow.foodDeliveryApp.controller;

import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String greet(HttpServletRequest request){
        return "hey iam rashi" + request.getSession().getId();
    }

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return service.register(user);
    }

    @PostMapping("/authenticate")
    public String login(@RequestBody Users user){
        return service.verify(user);
    }


}
