package com.eatNow.foodDeliveryApp.repository;


import com.eatNow.foodDeliveryApp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface OrderRepo extends JpaRepository<Order,Long> {

    public List<Order> findByCustomerId(Long userId);

    public List<Order> findByRestaurantId(Long restaurantId);

}
