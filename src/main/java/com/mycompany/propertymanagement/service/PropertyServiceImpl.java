package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDto;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessLogicException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.AddressRepository;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService{
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;


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
        var addressEntity = propertyConverter.extractAddressEntityFromPropertyDto(propertyDto);

        addressEntity = addressRepository.save(addressEntity);
        propertyEntity.setAddress(addressEntity);

        Optional<UserEntity> owner = userRepository.findById(propertyDto.getOwnerId());

        if(owner.isPresent()) {
            propertyEntity.setUserEntity(owner.get());
        }else {
            var errors = new ArrayList<ErrorModel>();
            var error = new ErrorModel();
            error.setMessage("User does not exist");
            error.setErrorCode("INVALID_USER_ERROR");
            errors.add(error);

            throw new BusinessLogicException(errors);
        }

        propertyEntity  = propertyRepository.save(propertyEntity);
        return propertyConverter.convertEntityToDTO(propertyEntity);
    }
    @Override
    public List<PropertyDto> getAllProperties() {
        List<PropertyEntity> propEntityList = (List<PropertyEntity>) propertyRepository.findAll();

        return propEntityList.stream()
                .map(a -> propertyConverter.convertEntityToDTO(a))
                .toList();
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
            case "price":
                propertyEntity.setPrice(propertyDto.getPrice());
                break;
            default:
                break;
        }
        propertyRepository.save(propertyEntity);
        return propertyConverter.convertEntityToDTO(propertyEntity);
    }
    @Override
    public List<PropertyDto> getAllProperiesByUserId(Long userId) {
        List<PropertyEntity> propEntityList = propertyRepository.getAllByUserEntityId(userId);

        return propEntityList.stream()
                .map(a -> propertyConverter.convertEntityToDTO(a))
                .toList();
    }
}
