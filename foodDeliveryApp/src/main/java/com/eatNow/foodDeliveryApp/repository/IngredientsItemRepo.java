package com.eatNow.foodDeliveryApp.repository;



import com.eatNow.foodDeliveryApp.model.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IngredientsItemRepo extends JpaRepository<IngredientsItem,Long> {

    List<IngredientsItem> findByRestaurantId(Long restaurantId);

}
