package com.eatNow.foodDeliveryApp.service;

import com.eatNow.foodDeliveryApp.model.USER_ROLE;
import com.eatNow.foodDeliveryApp.model.UserPrinciple;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.repository.UserRepo;
import com.eatNow.foodDeliveryApp.request.AuthenticationRequest;
import com.eatNow.foodDeliveryApp.request.RegisterRequest;
import com.eatNow.foodDeliveryApp.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepo repo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;





    public RegisterRequest register(RegisterRequest request){
        System.out.println(request.getRole());
        try {
            var user = Users.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
            System.out.println(request.getRole());
            repo.save(user);
            return request;

        }catch (Exception e){
            throw new RuntimeException("Registration failed: " + e.getMessage());
        }


    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

            if(authentication.isAuthenticated()) {
                var userPrinciple = (UserPrinciple) authentication.getPrincipal();
                var jwtToken = jwtService.generateToken(request.getEmail());
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .firstName(userPrinciple.getUser().getFirstName())
                        .build();
            }else{
                throw new RuntimeException("Authentication failed");
            }
        }catch (Exception e){
            throw new RuntimeException("invalid credentials");
        }
    }


    public Users findUserByJwtToken(String jwtToken){
        try {
            String username = jwtService.extractUserName(jwtToken);

            if(username!=null){
                return repo.findByEmail(username);
            }
            else {
                throw new RuntimeException("user not found");
            }
        }catch (Exception e){
            throw new RuntimeException("Failed to find user by JWT token: " + e.getMessage());
        }
    }

}
