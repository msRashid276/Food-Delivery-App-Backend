package com.eatNow.foodDeliveryApp.service;

import com.eatNow.foodDeliveryApp.model.Category;
import com.eatNow.foodDeliveryApp.model.Food;
import com.eatNow.foodDeliveryApp.model.Restaurant;
import com.eatNow.foodDeliveryApp.repository.FoodRepository;
import com.eatNow.foodDeliveryApp.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FoodServiceImp implements FoodService{

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant) {

        Food food = new Food();
        food.setCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(request.getDescription());
        food.setImages(request.getImages());
        food.setPrice(request.getPrice());
        food.setName(request.getName());
        food.setIngredients(request.getIngredientsItems());
        food.setSeasonal(request.isSeasonal());
        food.setVegetarian(request.isVegetarian());

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);
        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);

    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegetarian,
                                         boolean isNonVegetarian,
                                         boolean isSeasonal,
                                         String foodCategory) {


        List<Food> food = foodRepository.findByRestaurantId(restaurantId);

        if(isVegetarian){
            food=filterByVegetarian(food,isVegetarian);
        }

        if(isNonVegetarian){
            food=filterByNonVegetarian(food,isNonVegetarian);
        }

        if(isSeasonal){
            food=filterBySeasonal(food,isSeasonal);
        }

        if(foodCategory!=null && !foodCategory.equals("")){
            food=filterByCategory(food,foodCategory);
        }


        return food;
    }

    private List<Food> filterByCategory(List<Food> food, String foodCategory) {
       return food.stream().filter(foodResult->{
            if(foodResult.getCategory()!=null){
                return foodResult.getCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> food, boolean isSeasonal) {
        return food.stream().filter(foodResult-> foodResult.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVegetarian(List<Food> food, boolean isNonVegetarian) {
        return food.stream().filter(foodResult-> !foodResult.isVegetarian()).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> food, boolean isVegetarian) {
       return food.stream().filter(foodResult->foodResult.isVegetarian()==isVegetarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {

        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isEmpty()){
            throw new Exception("food not exist...");
        }
        return food.get();
    }

    @Override
    public Food updateAvailableStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
       return foodRepository.save(food);

    }
}
