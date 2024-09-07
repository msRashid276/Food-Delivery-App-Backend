package com.eatNow.foodDeliveryApp.controller;


import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<Users> getUserProfile(@RequestHeader("Authorization") String authHeader) throws Exception {

        System.out.println(authHeader);
        Users user = userService.findUserByAuthorizationHeader(authHeader);
        System.out.println(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
