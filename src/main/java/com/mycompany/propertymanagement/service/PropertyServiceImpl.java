package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDto;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService{
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public String outputProperty(PropertyDto propertyDto) {
        StringBuilder ret = new StringBuilder();
        ret.append(" Desc: " + propertyDto.getDescription());
        ret.append(" || Price: " + propertyDto.getPrice());
        ret.append(" || Id: " + propertyDto.getId());

        return ret.toString();
    }
    @Override
    public PropertyDto saveProperty(PropertyDto propertyDto) {
        var propertyEntity = propertyConverter.convertDTOToEntity(propertyDto);
        propertyEntity  = propertyRepository.save(propertyEntity);
        return propertyConverter.convertEntityToDTO(propertyEntity);
    }
    @Override
    public List<PropertyDto> getAllProperties() {
        List<PropertyEntity> propEntityList = (List<PropertyEntity>) propertyRepository.findAll();

        var propDtoList = propEntityList.stream()
                .map(a -> propertyConverter.convertEntityToDTO(a))
                .collect(Collectors.toList());

        return propDtoList;
    }
    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
    @Override
    public PropertyDto updateProperty(PropertyDto propertyDto, Long propertyId) {
        Optional<PropertyEntity> optPe = propertyRepository.findById(propertyId);
        PropertyDto dto = null;
        if(optPe.isPresent()){
            var propertyEntity = optPe.get();
            PropertyConverter.setEntityPropertiesFromDto(propertyEntity,propertyDto);
            propertyRepository.save(propertyEntity);
            dto = propertyConverter.convertEntityToDTO(propertyEntity);
        }
        return dto;
    }
    @Override
    public PropertyDto patchProperty(PropertyDto propertyDto, String propertyName, Long propertyId) {
        Optional<PropertyEntity> optPe = propertyRepository.findById(propertyId);
        if(optPe.isEmpty()) return null;
        PropertyEntity propertyEntity = optPe.get();
        switch (propertyName){
            case "title":
                propertyEntity.setTitle(propertyDto.getTitle());
                break;
            case "description":
                propertyEntity.setDescription(propertyDto.getDescription());
                break;
            case "ownerName":
                propertyEntity.setOwnerName(propertyDto.getOwnerName());
                break;
            case "ownerEmail":
                propertyEntity.setOwnerEmail(propertyDto.getOwnerEmail());
                break;
            case "price":
                propertyEntity.setPrice(propertyDto.getPrice());
                break;
            case "address":
                propertyEntity.setAddress(propertyDto.getAddress());
                break;
            default:
                break;
        }
        propertyRepository.save(propertyEntity);
        return propertyConverter.convertEntityToDTO(propertyEntity);
    }

}
