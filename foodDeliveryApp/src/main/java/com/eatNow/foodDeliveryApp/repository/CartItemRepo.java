package com.eatNow.foodDeliveryApp.repository;

import com.eatNow.foodDeliveryApp.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long> {
}
