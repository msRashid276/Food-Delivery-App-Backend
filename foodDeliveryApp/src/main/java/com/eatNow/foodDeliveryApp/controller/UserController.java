package com.eatNow.foodDeliveryApp.controller;

import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.request.AuthenticationRequest;
import com.eatNow.foodDeliveryApp.request.RegisterRequest;
import com.eatNow.foodDeliveryApp.response.AuthenticationResponse;
import com.eatNow.foodDeliveryApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService service;



    @PostMapping("/register")
    public ResponseEntity<RegisterRequest> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }


}
