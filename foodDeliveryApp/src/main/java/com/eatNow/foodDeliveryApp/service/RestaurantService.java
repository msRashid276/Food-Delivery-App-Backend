package com.eatNow.foodDeliveryApp.service;

import com.eatNow.foodDeliveryApp.dto.RestaurantDto;
import com.eatNow.foodDeliveryApp.model.Restaurant;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.request.CreateRestaurantRequest;


import java.util.List;


public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest request,Users user);

    public Restaurant updateRestaurant(Long restaurantId,CreateRestaurantRequest updatedRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavourites(Long restaurantId, Users user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;

}
