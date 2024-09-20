package com.eatNow.foodDeliveryApp.repository;

import com.eatNow.foodDeliveryApp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {
}
