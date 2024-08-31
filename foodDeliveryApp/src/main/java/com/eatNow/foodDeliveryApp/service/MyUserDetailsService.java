package com.eatNow.foodDeliveryApp.service;

import com.eatNow.foodDeliveryApp.model.USER_ROLE;
import com.eatNow.foodDeliveryApp.model.UserPrinciple;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = repo.findByEmail(username);

        if(user==null){
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrinciple(user);


    }
}
