package com.mycompany.propertymanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessLogicException(BusinessLogicException ble){
        logger.debug("debug lvl : Business Logic handled");
        return new ResponseEntity<List<ErrorModel>>(ble.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve){

        logger.trace("trace lvl : Validation Error");//high
        logger.debug("debug lvl : Validation Error");
        logger.info("info lvl : Validation Error");
        logger.warn("warn lvl : Validation Error");
        logger.error("error lvl : Validation Error");// lowest lvl
        //higher lvl logger gets all its lower level counterparts

        List<FieldError> fieldErrorList = manve.getBindingResult().getFieldErrors();
        List<ErrorModel> errorModelList = fieldErrorList.stream().map((fieldError) -> {
            ErrorModel e = new ErrorModel();
            e.setMessage(fieldError.getDefaultMessage());
            e.setErrorCode(fieldError.getField());
            return e;
        }).collect(Collectors.toList());
        return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST);
    }
}
