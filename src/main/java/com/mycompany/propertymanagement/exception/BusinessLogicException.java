package com.mycompany.propertymanagement.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BusinessLogicException extends RuntimeException{

    private List<ErrorModel> errors;

    public BusinessLogicException(List<ErrorModel> errors) {
        this.errors = errors;
    }
}
