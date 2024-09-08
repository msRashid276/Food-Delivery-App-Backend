package com.eatNow.foodDeliveryApp.controller;


import com.eatNow.foodDeliveryApp.model.Food;
import com.eatNow.foodDeliveryApp.model.Restaurant;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.request.CreateFoodRequest;
import com.eatNow.foodDeliveryApp.service.FoodService;
import com.eatNow.foodDeliveryApp.service.RestaurantService;
import com.eatNow.foodDeliveryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/foods")
public class FoodController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FoodService foodService;

    @GetMapping("/search")
    public ResponseEntity <List<Food>> searchRestaurant(@RequestParam String keyword, @RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<Food> foods = foodService.searchFood(keyword);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity <List<Food>> getRestaurantsFood(@RequestParam Long restaurantId,
                                                          @RequestParam boolean vegetarian,
                                                          @RequestParam boolean seasonal,
                                                          @RequestParam boolean nonVeg,
                                                          @RequestParam(required = false) String food_category,
                                                          @RequestHeader("Authorization") String authHeader
    ) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<Food> foods = foodService.getRestaurantsFood(restaurantId,vegetarian,seasonal,nonVeg,food_category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }



}
