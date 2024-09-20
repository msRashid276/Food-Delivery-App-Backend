package com.eatNow.foodDeliveryApp.request;


import com.eatNow.foodDeliveryApp.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;

    private Address address;
}
