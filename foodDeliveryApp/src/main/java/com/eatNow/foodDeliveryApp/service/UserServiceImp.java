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
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private MyUserDetailsService myUserDetailsService;



    public RegisterRequest register(RegisterRequest request) {
        Users existingUser = repo.findByEmail(request.getEmail());

        if (existingUser != null) {
            throw new IllegalStateException("Email is already used with another account");
        }

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

            //  Load user by username
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getEmail());

            //  Check the user exists
            if (userDetails == null) {
                throw new RuntimeException("Invalid username");
            }

            // Verifying the password
            if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
                throw new RuntimeException("Invalid password");
            }

            // Authenticate the user using authentication manager
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

            // Generate JWT token if authentication is successful
            if(authentication.isAuthenticated()) {
                var userPrinciple = (UserPrinciple) authentication.getPrincipal();
                var jwtToken = jwtService.generateToken(authentication);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .firstName(userPrinciple.getUser().getFirstName())
                        .role(String.valueOf(userPrinciple.getUser().getRole()))
                        .build();
            }else{
                throw new RuntimeException("Authentication failed");
            }
        }catch (Exception e){
            throw new RuntimeException("invalid credentials");
        }
    }




    public Users findUserByAuthorizationHeader(String authHeader){
        try {
            String username = jwtService.extractUserName(authHeader.substring(7));
            System.out.println(username);

            return null;
//            if(username!=null){
//                return repo.findByEmail(username);
//            }
//            else {
//                throw new RuntimeException("user not found");
//            }
        }catch (Exception e){
            throw new RuntimeException("Failed to find user by JWT token: " + e.getMessage());
        }
    }

}
