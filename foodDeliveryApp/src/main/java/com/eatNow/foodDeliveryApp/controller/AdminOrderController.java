package com.eatNow.foodDeliveryApp.controller;


import com.eatNow.foodDeliveryApp.model.Order;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.request.OrderRequest;
import com.eatNow.foodDeliveryApp.service.OrderService;
import com.eatNow.foodDeliveryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;



    @GetMapping("/res_owner/order/{restaurantId}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long restaurantId,@RequestParam(required = false) String order_status,@RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);

        List<Order> order = orderService.getRestaurantOrders(user.getId(),order_status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/res_owner/order/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId,
                                                         @PathVariable String orderStatus,
                                                         @RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);

        Order order = orderService.updateOrder(orderId,orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
