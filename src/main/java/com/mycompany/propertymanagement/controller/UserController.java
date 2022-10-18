package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.UserDto;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserDto userDto){
        userDto = userService.registerUser(userDto);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }
}
