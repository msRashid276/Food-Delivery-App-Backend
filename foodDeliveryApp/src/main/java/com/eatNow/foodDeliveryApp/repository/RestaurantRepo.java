package com.eatNow.foodDeliveryApp.repository;

import com.eatNow.foodDeliveryApp.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {

    @Query("SELECT r FROM Restaurant r WHERE "+
            "LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%'))"+
            "OR LOWER(r.cuisineType) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Restaurant> findBySearchQuery(String query);

    Restaurant findByOwnerId(Long userId);

}
