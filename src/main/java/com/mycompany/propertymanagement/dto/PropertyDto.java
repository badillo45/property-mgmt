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
    private String address;

    @JsonProperty("propId")
    private Long id;

}
