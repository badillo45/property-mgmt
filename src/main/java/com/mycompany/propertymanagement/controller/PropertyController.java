package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDto;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Value("${ppty.test:}")//application.properties .can be used at any file. use colon after for optional
    String testProperty;

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/greet")
    public String getContent() {
        System.out.println(testProperty);
        return "Yay!! Peace ! Peace!";
    }

    @GetMapping("/test-prop")
    public ResponseEntity getDisplay(@RequestBody PropertyDto prop) {

        String ret = propertyService.outputProperty(prop);
        return new ResponseEntity<String>(ret, HttpStatus.FOUND);
    }

    @GetMapping("/property")
    public ResponseEntity<List<PropertyDto>> getAllProperties() {
        List<PropertyDto> propertyList = propertyService.getAllProperties();
        return new ResponseEntity<List<PropertyDto>>(propertyList, HttpStatus.OK);
    }

    @PostMapping("/property")
    public ResponseEntity<PropertyDto> saveProperty(@RequestBody PropertyDto propertyDto) {
        PropertyDto pDto = propertyService.saveProperty(propertyDto);
        return new ResponseEntity<PropertyDto>(pDto, HttpStatus.OK);
    }
    @PutMapping("/property/{propertyId}")
    public ResponseEntity<PropertyDto> updateProperty(@RequestBody PropertyDto prop,@PathVariable Long propertyId) {
        PropertyDto pDto = propertyService.updateProperty(prop,propertyId);
        return new ResponseEntity<PropertyDto>(pDto, HttpStatus.OK);
    }
    @DeleteMapping("/property/{propertyId}")
    public ResponseEntity deleteProperty(@PathVariable Long propertyId) {
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/property/{propertyName}/{propertyId}")
    public ResponseEntity patchProperty(@RequestBody PropertyDto propertyDto, @PathVariable String propertyName,@PathVariable Long propertyId) {
        PropertyDto pDto = propertyService.patchProperty(propertyDto,propertyName,propertyId);
        if(pDto != null){
            return new ResponseEntity<>(pDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}