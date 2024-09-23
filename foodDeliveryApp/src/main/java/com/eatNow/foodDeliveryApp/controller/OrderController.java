package com.eatNow.foodDeliveryApp.controller;


import com.eatNow.foodDeliveryApp.model.CartItem;
import com.eatNow.foodDeliveryApp.model.Order;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.request.AddCartItemRequest;
import com.eatNow.foodDeliveryApp.request.OrderRequest;
import com.eatNow.foodDeliveryApp.service.OrderService;
import com.eatNow.foodDeliveryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request, @RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);

        Order order = orderService.createOrder(request,user);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


    @GetMapping("order")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<Order> order = orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
