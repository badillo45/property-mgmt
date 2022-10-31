package com.mycompany.propertymanagement.converter;

import com.mycompany.propertymanagement.dto.UserDto;
import com.mycompany.propertymanagement.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public static UserEntity convertUserDtoToEntity (UserDto userDto){
        if(userDto == null) return null;
        UserEntity userEntity = new UserEntity();
        setUserEntityPropertiesFromDto(userEntity, userDto);
        return userEntity;
    }
    public static UserDto convertUserEntityToDto(UserEntity userEntity){
        if(userEntity == null) return null;
        var userDto = new UserDto();
        userDto.setUserName(userEntity.getUserName());
        userDto.setId(userEntity.getId());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword("********");
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setContactNo(userEntity.getContactNo());
        userDto.setLastName(userEntity.getLastName());
        userDto.setRegisteredDt(userEntity.getRegisteredDt());
        return userDto;
    }

    public static void setUserEntityPropertiesFromDto(UserEntity userEntity, UserDto userDto){
        userEntity.setContactNo(userDto.getContactNo());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setUserName(userDto.getUserName());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setRegisteredDt(userDto.getRegisteredDt());
    }
}
