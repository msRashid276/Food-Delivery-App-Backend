package com.eatNow.foodDeliveryApp.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Embeddable
@Component
public class RestaurantDto {

    private String title;

    @Column(length = 1000)
    private List<String> images;

    private String description;

    private Long id;

}
