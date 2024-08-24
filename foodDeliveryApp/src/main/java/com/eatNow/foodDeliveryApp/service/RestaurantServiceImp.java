package com.eatNow.foodDeliveryApp.service;

import com.eatNow.foodDeliveryApp.dto.RestaurantDto;
import com.eatNow.foodDeliveryApp.model.Restaurant;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.repository.AddressRepo;
import com.eatNow.foodDeliveryApp.repository.RestaurantRepo;
import com.eatNow.foodDeliveryApp.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    private AddressRepo addressRepo;
    public RestaurantServiceImp(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Autowired
    private UserService userService;





    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, Users user) {
        return null;
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        return null;
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return List.of();
    }

    @Override
    public List<Restaurant> searchRestaurant() {
        return List.of();
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        return null;
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public RestaurantDto addToFavourites(Long restaurantId, Users user) throws Exception {
        return null;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        return null;
    }
}
