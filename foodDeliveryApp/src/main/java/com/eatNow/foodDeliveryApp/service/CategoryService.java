package com.eatNow.foodDeliveryApp.service;


import com.eatNow.foodDeliveryApp.model.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory(String name,Long userId) throws Exception;

    public List<Category> findCategoryByRestaurantIdFromUserId(Long userId) throws Exception;

    public Category findCategoryById(Long id) throws Exception;

}
