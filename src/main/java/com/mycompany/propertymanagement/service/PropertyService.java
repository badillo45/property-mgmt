package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.PropertyDto;

import java.util.List;

public interface PropertyService {
    String outputProperty(PropertyDto propertyDto);
    PropertyDto saveProperty(PropertyDto propertyDto);

    List<PropertyDto> getAllProperties();

    void deleteProperty(Long propertyId);
    PropertyDto updateProperty(PropertyDto propertyDto, Long propertyId);
    PropertyDto patchProperty(PropertyDto propertyDto, String propertyName, Long propertyId);


}
