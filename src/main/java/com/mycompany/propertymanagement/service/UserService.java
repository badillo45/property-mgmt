package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.UserDto;
import org.apache.catalina.User;

public interface UserService {
    UserDto registerUser(UserDto userDto);

    UserDto login(UserDto userDto);
}
