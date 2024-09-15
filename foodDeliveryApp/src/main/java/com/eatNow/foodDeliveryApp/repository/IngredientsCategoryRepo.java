package com.eatNow.foodDeliveryApp.repository;

import com.eatNow.foodDeliveryApp.model.IngredientsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IngredientsCategoryRepo extends JpaRepository<IngredientsCategory,Long> {

    List<IngredientsCategory> findByRestaurantId(Long restaurantId);
}
