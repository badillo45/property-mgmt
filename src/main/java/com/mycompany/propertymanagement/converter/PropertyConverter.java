package com.mycompany.propertymanagement.converter;

import com.mycompany.propertymanagement.dto.PropertyDto;
import com.mycompany.propertymanagement.entity.AddressEntity;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {
    public PropertyEntity convertDTOToEntity(PropertyDto propertyDto){
        if(propertyDto ==null) return null;

        PropertyEntity propertyEntity = new PropertyEntity();
        setEntityPropertiesFromDto(propertyEntity,propertyDto);

        return propertyEntity;
    }

    public PropertyDto convertEntityToDTO(PropertyEntity propertyEntity){
        if(propertyEntity ==null) return null;

        PropertyDto propertyDto = new PropertyDto();

        propertyDto.setId(propertyEntity.getId());
        propertyDto.setTitle(propertyEntity.getTitle());
        propertyDto.setDescription(propertyEntity.getDescription());
        propertyDto.setPrice(propertyEntity.getPrice());

        return propertyDto;
    }

    public AddressEntity extractAddressEntityFromPropertyDto(PropertyDto propertyDto){
        if(propertyDto ==null) return null;

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(propertyDto.getCity());
        addressEntity.setHouseNo(propertyDto.getHouseNo());
        addressEntity.setPostalCode(propertyDto.getPostalCode());
        addressEntity.setStreetName(propertyDto.getStreetName());

        return addressEntity;
    }

    public static void setEntityPropertiesFromDto(PropertyEntity propertyEntity, PropertyDto propertyDto){

        propertyEntity.setTitle(propertyDto.getTitle());
        propertyEntity.setDescription(propertyDto.getDescription());
        propertyEntity.setPrice(propertyDto.getPrice());
    }
}
