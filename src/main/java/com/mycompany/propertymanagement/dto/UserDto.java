
package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;
    @NotEmpty
    @Length(min = 1,max = 50, message = "The username must be 1-50 characters")
    private String userName;
    private String firstName;
    private String lastName;
    @NotNull(message = "Please input email")
    private String email;
    private String contactNo;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    private Date registeredDt;
    private String nullProperty; //will not show on JSON because of annotation
}
