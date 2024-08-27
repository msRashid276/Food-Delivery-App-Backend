package com.eatNow.foodDeliveryApp.service;


import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.request.AuthenticationRequest;
import com.eatNow.foodDeliveryApp.request.RegisterRequest;
import com.eatNow.foodDeliveryApp.response.AuthenticationResponse;
import org.springframework.stereotype.Service;


public interface UserService {


    public RegisterRequest register(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);

    public Users findUserByJwtToken(String jwtToken);

}
