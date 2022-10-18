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
        return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto userDto){
        userDto = userService.login(userDto);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    @PostMapping("/log_in")
    public ResponseEntity loginDual(@RequestBody UserDto userDto){
        userDto = userService.loginDual(userDto);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }
}
