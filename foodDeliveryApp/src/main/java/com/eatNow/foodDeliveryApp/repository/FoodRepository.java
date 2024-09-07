package com.eatNow.foodDeliveryApp.repository;

import com.eatNow.foodDeliveryApp.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {

    List<Food> findByRestaurantId(Long RestaurantId);

    @Query("SELECT f FROM Food f WHERE "+
            "LOWER(f.name) LIKE LOWER(CONCAT('%', :keyword, '%'))"+
            "OR LOWER(f.foodCategory.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Food> searchFood(String keyword);
}
