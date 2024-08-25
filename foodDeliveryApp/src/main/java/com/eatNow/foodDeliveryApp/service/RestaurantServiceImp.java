package com.eatNow.foodDeliveryApp.service;

import com.eatNow.foodDeliveryApp.dto.RestaurantDto;
import com.eatNow.foodDeliveryApp.model.Address;
import com.eatNow.foodDeliveryApp.model.Restaurant;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.repository.AddressRepo;
import com.eatNow.foodDeliveryApp.repository.RestaurantRepo;
import com.eatNow.foodDeliveryApp.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

        Address address = addressRepo.save(request.getAddress());

        Restaurant restaurant = new Restaurant();

        restaurant.setAddress(address);
        restaurant.setContactInformation(request.getContactInformation());
        restaurant.setName(request.getName());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setDescription(request.getDescription());
        restaurant.setImages(request.getImages());
        restaurant.setOpeningHours(request.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepo.save(restaurant);
    }


    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if(restaurant.getAddress()!=null){
            restaurant.setAddress(updatedRestaurant.getAddress());
        }
        if(restaurant.getContactInformation()!=null){
            restaurant.setContactInformation(updatedRestaurant.getContactInformation());
        }
        if(restaurant.getName()!=null){
            restaurant.setName(updatedRestaurant.getName());
        }
        if(restaurant.getDescription()!=null){
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        return restaurantRepo.save(restaurant);
    }


    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepo.delete(restaurant);

    }


    @Override
    public List<Restaurant> getAllRestaurant() {
       return restaurantRepo.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepo.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {

        Optional<Restaurant> restaurant = restaurantRepo.findById(id);
        if(restaurant.isEmpty()){
            throw new Exception("restaurant is not found with this id"+id);
        }
        return restaurant.get();
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
