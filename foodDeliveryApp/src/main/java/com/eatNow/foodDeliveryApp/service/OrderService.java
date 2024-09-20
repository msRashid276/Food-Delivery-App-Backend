package com.eatNow.foodDeliveryApp.service;


import com.eatNow.foodDeliveryApp.model.Order;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.request.OrderRequest;

import java.util.List;

public interface OrderService {

    public Order createOrder(OrderRequest request, Users user) throws Exception;

    public Order updateOrder(Long orderId, String orderStatus) throws Exception;

    public Order cancelOrder(Long orderId) throws Exception;

    public List<Order> getUserOrders(Long userId) throws Exception;

    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;
}
