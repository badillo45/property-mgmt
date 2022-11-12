package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PropertyDto {
    private String title;
    private String description;
    @NotBlank
    @Min(0)
    private Double price;
    //address properties
    private String houseNo;
    @NotBlank
    private String streetName;
    @NotBlank
    private String city;
    @NotBlank
    @Length(min =4, max=4, message = "Postal Code consists of 4 digits")
    private String postalCode;
    private Long ownerId;

    @JsonProperty("propId")
    private Long id;

}
