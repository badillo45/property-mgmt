package com.mycompany.propertymanagement.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calcu")
public class CalculatorController {
    @GetMapping("/divide")
    public Double quotient(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2){
        return num1/num2;
    }
    @GetMapping("/multiply/{pNum1}/{pNum2}")
    public Double product(@PathVariable("pNum1") Double num1, @PathVariable("pNum2") Double num2){
        return num1*num2;
    }
    //bad request if one param is missing
    @GetMapping("/add/{num1}")
    public Double product(@PathVariable("num1") Double num1, @RequestParam("num2") Double num2,@RequestParam("num3") Double num3){
        return num1+num2+num3;
    }
}
