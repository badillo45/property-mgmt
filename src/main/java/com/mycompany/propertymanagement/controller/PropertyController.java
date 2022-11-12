package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDto;
import com.mycompany.propertymanagement.service.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    String applicationPropertyTest;

    @Autowired
    private PropertyService propertyService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/greet")
    public String getContent() {
        logger.debug(applicationPropertyTest);
        return "Yay!! Peace ! Peace!";
    }

    @GetMapping("/test-prop")
    public ResponseEntity<String> getDisplay(@RequestBody PropertyDto prop) {

        String ret = propertyService.outputProperty(prop);
        return new ResponseEntity<>(ret, HttpStatus.FOUND);
    }

    @GetMapping("/property")
    public ResponseEntity<Object> getAllProperties() {
        List<PropertyDto> propertyList = propertyService.getAllProperties();

        if(propertyList != null && propertyList.isEmpty())
            return new ResponseEntity<>("No Property Found.", HttpStatus.I_AM_A_TEAPOT);
        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }

    @PostMapping("/property")
    public ResponseEntity<PropertyDto> saveProperty(@RequestBody PropertyDto propertyDto) {
        PropertyDto pDto = propertyService.saveProperty(propertyDto);
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }
    @PutMapping("/property/{propertyId}")
    public ResponseEntity<PropertyDto> updateProperty(@RequestBody PropertyDto prop,@PathVariable Long propertyId) {
        PropertyDto pDto = propertyService.updateProperty(prop,propertyId);
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }
    @DeleteMapping("/property/{propertyId}")
    public ResponseEntity<PropertyDto> deleteProperty(@PathVariable Long propertyId) {
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/property/{propertyName}/{propertyId}")
    public ResponseEntity<PropertyDto> patchProperty(@RequestBody PropertyDto propertyDto, @PathVariable String propertyName,@PathVariable Long propertyId) {
        PropertyDto pDto = propertyService.patchProperty(propertyDto,propertyName,propertyId);
        if(pDto != null){
            return new ResponseEntity<>(pDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/property/user/{userId}")
    public ResponseEntity<Object> getAllPropertiesByUserId(@PathVariable Long userId) {
        List<PropertyDto> propertyList = propertyService.getAllProperiesByUserId(userId);

        if(propertyList != null && propertyList.isEmpty())
            return new ResponseEntity<>("No Property Found.", HttpStatus.I_AM_A_TEAPOT);
        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }
}