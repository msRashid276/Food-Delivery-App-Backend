package com.eatNow.foodDeliveryApp.repository;

import com.eatNow.foodDeliveryApp.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {

    List<Food> findByRestaurantId(Long restaurantId);

    @Query("SELECT f FROM Food f WHERE "+
            "LOWER(f.name) LIKE LOWER(CONCAT('%', :keyword, '%'))"+
            "OR LOWER(f.category.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Food> searchFood(String keyword);
}
