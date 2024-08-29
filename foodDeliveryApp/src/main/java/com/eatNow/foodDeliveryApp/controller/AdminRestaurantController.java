package com.eatNow.foodDeliveryApp.controller;


import com.eatNow.foodDeliveryApp.model.Restaurant;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.request.CreateRestaurantRequest;
import com.eatNow.foodDeliveryApp.response.MessageResponse;
import com.eatNow.foodDeliveryApp.service.RestaurantService;
import com.eatNow.foodDeliveryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @PostMapping("/test")
    public String greet(){
        return "hey iam a coder";
    }

    @PostMapping("/restaurants")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest request,@RequestHeader("Authorization") String jwt){
        System.out.println(request);
        System.out.println(jwt);
        Users user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.createRestaurant(request,user);
        return  new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long restaurantId,@RequestHeader("Authorization") String jwt, @RequestBody CreateRestaurantRequest updateRequest) throws Exception {
        Users user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurant(restaurantId,updateRequest);
        return  new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(@PathVariable Long restaurantId,@RequestHeader("Authorization") String jwt) throws Exception {
        Users user = userService.findUserByJwtToken(jwt);

        restaurantService.deleteRestaurant(restaurantId);

        MessageResponse response = new MessageResponse();
        response.setMessage("restaurant deleted succesfully");
        return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(@PathVariable Long restaurantId,@RequestHeader("Authorization") String jwt) throws Exception {
        Users user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurantStatus(restaurantId);


        return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(@RequestHeader("Authorization") String jwt

    ) throws Exception {
        Users user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
        return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }





}
