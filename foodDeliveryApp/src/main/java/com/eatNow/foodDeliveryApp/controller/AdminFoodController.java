package com.eatNow.foodDeliveryApp.controller;


import com.eatNow.foodDeliveryApp.model.Food;
import com.eatNow.foodDeliveryApp.model.Restaurant;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.request.CreateFoodRequest;
import com.eatNow.foodDeliveryApp.response.MessageResponse;
import com.eatNow.foodDeliveryApp.service.FoodService;
import com.eatNow.foodDeliveryApp.service.RestaurantService;
import com.eatNow.foodDeliveryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/res_owner")
public class AdminFoodController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FoodService foodService;

    @PostMapping("/foods")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest request, @RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);
        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());

       Food food = foodService.createFood(request,request.getCategory(),restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/food/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);

        foodService.deleteFood(id);

        MessageResponse response = new MessageResponse();
        response.setMessage("food deleted successfully");
        return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);

    }

    @PutMapping("/food/{id}")
    public ResponseEntity<Food> updateAvailableStatus(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);
        Food food = foodService.updateAvailableStatus(id);

        return new ResponseEntity<>(food,HttpStatus.OK);

    }



}
