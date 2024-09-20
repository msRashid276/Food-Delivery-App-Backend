package com.eatNow.foodDeliveryApp.repository;

import com.eatNow.foodDeliveryApp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {

    public Cart findByCustomerId(Long userId);

}
