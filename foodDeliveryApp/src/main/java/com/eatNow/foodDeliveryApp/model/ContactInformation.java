package com.eatNow.foodDeliveryApp.model;



import jakarta.persistence.Embeddable;
import lombok.Data;


@Embeddable
@Data
public class ContactInformation {

    private String email;

    private String mobile;

    private String twitter;

    private String instagram;
}
