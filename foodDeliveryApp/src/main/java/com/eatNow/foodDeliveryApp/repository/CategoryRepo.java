package com.eatNow.foodDeliveryApp.repository;

import com.eatNow.foodDeliveryApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    public List<Category> findByRestaurantId(Long id);

}
