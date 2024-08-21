package com.eatNow.foodDeliveryApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Users owner;

    private String name;

    private String description;

    private String cuisineType;

    @OneToOne
    private Address address;

    private ContactInformation contactInformation;



}
