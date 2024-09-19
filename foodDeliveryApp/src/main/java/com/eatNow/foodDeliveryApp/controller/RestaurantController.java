package com.eatNow.foodDeliveryApp.controller;


import com.eatNow.foodDeliveryApp.dto.RestaurantDto;
import com.eatNow.foodDeliveryApp.model.Restaurant;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.service.JWTService;
import com.eatNow.foodDeliveryApp.service.RestaurantService;
import com.eatNow.foodDeliveryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/restaurants")
public class RestaurantController {



    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestParam String keyword, @RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurants(@RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<Restaurant> restaurant = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@RequestHeader("Authorization") String authHeader,@PathVariable Long id) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favourites")
    public ResponseEntity<RestaurantDto> addToFavourites(@RequestHeader("Authorization") String authHeader,@PathVariable Long id) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        RestaurantDto restaurant = restaurantService.addToFavourites(id,user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }



}
