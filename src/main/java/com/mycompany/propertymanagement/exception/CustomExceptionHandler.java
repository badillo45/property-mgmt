package com.mycompany.propertymanagement.exception;

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

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessLogicException(BusinessLogicException ble){
        System.out.println("BL handled");
        return new ResponseEntity<List<ErrorModel>>(ble.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve){
        System.out.println("VAlidation error");
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
