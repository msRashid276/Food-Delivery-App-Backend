package com.eatNow.foodDeliveryApp.request;

import lombok.Data;

@Data
public class IngredientsItemRequest {

    private String name;
    private Long categoryId;
    private Long restaurantId;

}
