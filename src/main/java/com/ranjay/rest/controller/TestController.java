package com.ranjay.rest.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TestController{
    @GetMapping(value="/test")
    public String testMethod(  ) {
        return "Welcome to Spring core app";
    }
    
}