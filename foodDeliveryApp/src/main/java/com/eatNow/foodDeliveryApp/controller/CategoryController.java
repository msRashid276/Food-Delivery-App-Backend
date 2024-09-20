package com.eatNow.foodDeliveryApp.controller;


import com.eatNow.foodDeliveryApp.model.Category;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.service.CategoryService;
import com.eatNow.foodDeliveryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @PostMapping("/res_owner/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category, @RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);
       Category createdCategory = categoryService.createCategory(category.getName(),user.getId());

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }



    @GetMapping("/user/category")
    public ResponseEntity<List<Category>> getRestaurantCategoryByUserId( @RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);
        List<Category> category  = categoryService.findCategoryByRestaurantIdFromUserId(user.getId());

        return new ResponseEntity<>(category, HttpStatus.OK);
    }



    @GetMapping("/res_owner/category/{categoryId}")
    public ResponseEntity<Category> getCategoryByCategoryID(@PathVariable Long categoryId, @RequestHeader("Authorization") String authHeader) throws Exception {

        Users user = userService.findUserByAuthorizationHeader(authHeader);
        Category findCategory = categoryService.findCategoryById(categoryId);

        return new ResponseEntity<>(findCategory, HttpStatus.CREATED);
    }



}
