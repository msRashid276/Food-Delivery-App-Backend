package com.eatNow.foodDeliveryApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {


    @GetMapping("/view")
    public String greet(){
        return "hey beauty";
    }

}
