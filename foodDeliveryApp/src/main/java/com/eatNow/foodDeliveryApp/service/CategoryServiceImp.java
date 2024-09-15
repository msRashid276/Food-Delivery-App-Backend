package com.eatNow.foodDeliveryApp.service;

import com.eatNow.foodDeliveryApp.model.Category;
import com.eatNow.foodDeliveryApp.model.Restaurant;
import com.eatNow.foodDeliveryApp.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {

        try {
            Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
            Category category = new Category();
            category.setName(name);
            category.setRestaurant(restaurant);

            return categoryRepo.save(category);
        } catch (Exception e) {
            // Loging the exception
            System.err.println("Error creating category: " + e.getMessage());

            // Rethrow the exception or handle it based on your requirement
            throw new Exception("Failed to create category", e);
        }
    }

    @Override
    public List<Category> findCategoryByRestaurantIdFromUserId(Long userId) throws Exception {

        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        return categoryRepo.findByRestaurantId(restaurant.getId());
    }


    @Override
    public Category findCategoryById(Long id) throws Exception {

        Optional<Category> optionalCategory = categoryRepo.findById(id);

        if(optionalCategory.isEmpty()){
            throw new Exception("Category not found with this id");
        }
        return optionalCategory.get();
    }
}
