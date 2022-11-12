package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDto;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessLogicException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto registerUser(UserDto userDto) {
        userDto.setRegisteredDt(new Date());

        validateUser(userDto);
        UserEntity userEntity = userRepository.save(UserConverter.convertUserDtoToEntity(userDto));
        return UserConverter.convertUserEntityToDto(userEntity);
    }

    private void validateUser(UserDto userDto) throws BusinessLogicException{
        Long userNameCount = userRepository.countByUserName(userDto.getUserName());
        Long emailCount = userRepository.countByEmail(userDto.getEmail());

        List<ErrorModel> errors = new ArrayList<>();
        ErrorModel error = null;

        if(userNameCount.equals(0L) && emailCount.equals(0L)) return;

        if(userNameCount.compareTo(1L) >= 0){
            error = new ErrorModel();
            error.setMessage("Username already exists");
            error.setErrorCode("REGISTER_ERROR");
            errors.add(error);
        }

        if(emailCount.compareTo(1L) >= 0){
            error = new ErrorModel();
            error.setMessage("Email already in use.");
            error.setErrorCode("REGISTER_ERROR");
            errors.add(error);
        }

        throw new BusinessLogicException(errors);
    }

    @Override
    public Optional<UserDto> login(UserDto userDto) {
        var result = userRepository.findByUserNameAndPassword(userDto.getUserName(),userDto.getPassword());

        return Optional.ofNullable(UserConverter.convertUserEntityToDto(result.orElse(null)));
    }

    @Override
    public Optional<UserDto> loginDual(UserDto userDto) {
        var result = userRepository.findByPasswordAndUserNameOrEmail(userDto.getPassword(),userDto.getUserName(),userDto.getEmail());
        return Optional.ofNullable(UserConverter.convertUserEntityToDto(result.orElse(null)));
    }
}
