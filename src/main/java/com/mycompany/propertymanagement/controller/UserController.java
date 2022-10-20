package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.UserDto;
import com.mycompany.propertymanagement.exception.BusinessLogicException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody UserDto userDto){
        userDto = userService.registerUser(userDto);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto userDto){
        var resultDto = userService.login(userDto);
        return checkLoginResults(resultDto);
    }

    @PostMapping("/log_in")
    public ResponseEntity loginDual(@RequestBody UserDto userDto){
        var resultDto = userService.loginDual(userDto);
        return checkLoginResults(resultDto);

    }

    private static ResponseEntity<UserDto> checkLoginResults(Optional<UserDto> resultDto) {
        if(resultDto.isPresent()){
            return new ResponseEntity<UserDto>(resultDto.get(), HttpStatus.OK);
        }else{
            List<ErrorModel> errors = new ArrayList<>();
            var error = new ErrorModel();
            error.setErrorCode("LOGIN_ERROR");
            error.setMessage("Invalid Credentials");
            errors.add(error);
            throw new BusinessLogicException(errors);
        }
    }
}
