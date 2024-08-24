package com.eatNow.foodDeliveryApp.repository;

import com.eatNow.foodDeliveryApp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {

}
