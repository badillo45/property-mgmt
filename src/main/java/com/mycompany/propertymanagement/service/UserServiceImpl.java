package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDto;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto registerUser(UserDto userDto) {
        userDto.setRegisteredDt(new Date());
        UserEntity userEntity = userRepository.save(UserConverter.convertUserDtoToEntity(userDto));
        return UserConverter.convertUserEntityToDto(userEntity);
    }

    @Override
    public UserDto login(UserDto userDto) {
        var result = userRepository.findByUserNameAndPassword(userDto.getUserName(),userDto.getPassword());

        return result.isPresent() ? UserConverter.convertUserEntityToDto(result.get()) : null;
    }

    @Override
    public UserDto loginDual(UserDto userDto) {
        var result = userRepository.findByPasswordAndUserNameOrEmail(userDto.getPassword(),userDto.getUserName(),userDto.getEmail());

        return result.isPresent() ? UserConverter.convertUserEntityToDto(result.get()) : null;
    }
}
