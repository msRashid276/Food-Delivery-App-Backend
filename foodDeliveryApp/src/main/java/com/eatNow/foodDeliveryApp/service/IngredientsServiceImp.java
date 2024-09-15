package com.eatNow.foodDeliveryApp.service;

import com.eatNow.foodDeliveryApp.model.IngredientsCategory;
import com.eatNow.foodDeliveryApp.model.IngredientsItem;
import com.eatNow.foodDeliveryApp.model.Restaurant;
import com.eatNow.foodDeliveryApp.repository.IngredientsCategoryRepo;
import com.eatNow.foodDeliveryApp.repository.IngredientsItemRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class IngredientsServiceImp implements IngredientsService{

    @Autowired
    private IngredientsCategoryRepo ingredientsCategoryRepo;

    @Autowired
    private IngredientsItemRepo ingredientsItemRepo;

    @Autowired
    private RestaurantService restaurantService;


//    -------------------------ingredientsCategoryServiceImplementation--------------------------------

    @Override
    public IngredientsCategory createIngredientsCategory(String ingredientsCategoryName, Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientsCategory ingredientsCategory = new IngredientsCategory();

        ingredientsCategory.setRestaurant(restaurant);
        ingredientsCategory.setName(ingredientsCategoryName);

        return ingredientsCategoryRepo.save(ingredientsCategory);
    }

    @Override
    public IngredientsCategory findIngredientsCategoryById(Long ingredientsCategoryId) throws Exception {

        Optional<IngredientsCategory> ingredientsCategory = ingredientsCategoryRepo.findById(ingredientsCategoryId);
        if(ingredientsCategory.isEmpty()){
            throw new Exception("ingredientsCategory is not found with this id"+ingredientsCategoryId);
        }
        return ingredientsCategory.get();
    }

    @Override
    public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long restaurantId) throws Exception {

        try{
           restaurantService.findRestaurantById(restaurantId);
           return ingredientsCategoryRepo.findByRestaurantId(restaurantId);
        }catch (Exception e){
            throw new Exception("Error occurred while fetching ingredient categories by restaurantId: " + e.getMessage());
        }

    }


    //    -------------------------ingredientsItemServiceImplementation--------------------------------


    @Override
    public IngredientsItem createIngredientsItem(Long restaurantId, Long ingredientsCategoryId, String ingredientsItemName) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientsCategory ingredientsCategory = findIngredientsCategoryById(ingredientsCategoryId);

        IngredientsItem ingredientsItem = new IngredientsItem();
        ingredientsItem.setName(ingredientsItemName);
        ingredientsItem.setRestaurant(restaurant);
        ingredientsItem.setCategory(ingredientsCategory);

        IngredientsItem addingIngredientsItem= ingredientsItemRepo.save(ingredientsItem);

        ingredientsCategory.getIngredientsItems().add(addingIngredientsItem);

        return addingIngredientsItem;
    }

    @Override
    public List<IngredientsItem> findIngredientsItemByRestaurantId(Long restaurantId) throws Exception {

        try{
            restaurantService.findRestaurantById(restaurantId);
            return ingredientsItemRepo.findByRestaurantId(restaurantId);
        }catch (Exception e){
            throw new Exception("Error occurred while fetching ingredient items by restaurantId: " + e.getMessage());
        }
    }

    @Override
    public IngredientsItem findIngredientsItemsById(Long ingredientsItemId) throws Exception {
        Optional<IngredientsItem> ingredientsItem = ingredientsItemRepo.findById(ingredientsItemId);
            if(ingredientsItem.isEmpty()){
                throw new Exception("ingredientsItem is not found with this id"+ingredientsItemId);
            }
             return ingredientsItem.get();
    }

    @Override
    public IngredientsItem updateStock(Long ingredientItemId) throws Exception {

    IngredientsItem ingredientsItem = findIngredientsItemsById(ingredientItemId);
    ingredientsItem.setInStoke(!ingredientsItem.isInStoke());
    
        return ingredientsItemRepo.save(ingredientsItem);
    }
}
