package com.eatNow.foodDeliveryApp.service;


import com.eatNow.foodDeliveryApp.model.IngredientsCategory;
import com.eatNow.foodDeliveryApp.model.IngredientsItem;

import java.util.List;


public interface IngredientsService {


    //IngredientsCategory

    public IngredientsCategory createIngredientsCategory(String ingredientsCategoryName,Long restaurantId) throws Exception;

    public IngredientsCategory findIngredientsCategoryById(Long ingredientsCategoryId) throws Exception;

    public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long restaurantId) throws Exception;



    //IngredientsItems

    public IngredientsItem createIngredientsItem(Long restaurantId, Long ingredientsCategoryId, String ingredientsItemName) throws Exception;

    public List<IngredientsItem> findIngredientsItemByRestaurantId(Long restaurantId) throws Exception;

    public IngredientsItem findIngredientsItemsById(Long ingredientsItemId) throws Exception;

    public IngredientsItem updateStock(Long ingredientItemId) throws Exception;


}
