package com.eatNow.foodDeliveryApp.controller;


import com.eatNow.foodDeliveryApp.model.IngredientsCategory;
import com.eatNow.foodDeliveryApp.model.IngredientsItem;
import com.eatNow.foodDeliveryApp.request.IngredientsCategoryRequest;
import com.eatNow.foodDeliveryApp.request.IngredientsItemRequest;
import com.eatNow.foodDeliveryApp.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientsController {

    @Autowired
    private IngredientsService ingredientsService;


//    -----------------------------ingredientsCategory-----------------------------------------


    @PostMapping("/category")
    public ResponseEntity<IngredientsCategory> createIngredientsCategory(
            @RequestBody IngredientsCategoryRequest request
    ) throws Exception {

        IngredientsCategory ingredientsCategory = ingredientsService.createIngredientsCategory(request.getName(),request.getRestaurantId());
        return new ResponseEntity<>(ingredientsCategory, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity <List<IngredientsCategory>> getIngredientsCategoryByRestaurantId(
            @PathVariable Long id
    ) throws Exception {

        List<IngredientsCategory> ingredientsCategory = ingredientsService.findIngredientsCategoryByRestaurantId(id);
        return new ResponseEntity<>(ingredientsCategory, HttpStatus.OK);
    }



//    -------------------------------ingredientsItem-------------------------------------------


    @PostMapping("/items")
    public ResponseEntity<IngredientsItem> createIngredientsItem(
            @RequestBody IngredientsItemRequest request
    ) throws Exception {

        IngredientsItem ingredientsItem = ingredientsService.createIngredientsItem(request.getRestaurantId(),request.getCategoryId(),request.getName());
        return new ResponseEntity<>(ingredientsItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItem> updateIngredientsItemStock(
            @PathVariable Long id
    ) throws Exception {

        IngredientsItem ingredientsItem = ingredientsService.updateStock(id);
        return new ResponseEntity<>(ingredientsItem, HttpStatus.OK);
    }


    @GetMapping("/restaurant/{id}")
    public ResponseEntity <List<IngredientsItem>> getIngredientsItemByRestaurantId(
            @PathVariable Long id
    ) throws Exception {

        List<IngredientsItem> ingredientsItem = ingredientsService.findIngredientsItemByRestaurantId(id);
        return new ResponseEntity<>(ingredientsItem, HttpStatus.OK);
    }



}
