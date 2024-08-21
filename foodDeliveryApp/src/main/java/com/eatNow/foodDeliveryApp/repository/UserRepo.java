package com.eatNow.foodDeliveryApp.repository;

import com.eatNow.foodDeliveryApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {

    Users findByEmail(String username);
}
