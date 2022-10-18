
package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private String password;
    private Date registeredDt;
    private String nullProperty; //will not show on JSON because of annotation
}
