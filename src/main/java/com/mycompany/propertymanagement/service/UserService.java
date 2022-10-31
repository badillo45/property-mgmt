package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.UserDto;

import java.util.Optional;

public interface UserService {
    UserDto registerUser(UserDto userDto);

    Optional<UserDto> login(UserDto userDto);

    Optional<UserDto> loginDual(UserDto userDto);
}
