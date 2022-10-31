package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {
    private String title;
    private String description;
    private Double price;
    //address properties
    private String houseNo;
    private String streetName;
    private String city;
    private String postalCode;
    private Long ownerId;

    @JsonProperty("propId")
    private Long id;

}
