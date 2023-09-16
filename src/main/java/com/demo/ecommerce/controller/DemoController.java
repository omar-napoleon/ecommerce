package com.demo.ecommerce.controller;

import com.demo.ecommerce.dto.EcommerceResponseDto;
import com.demo.ecommerce.service.EcommerceServiceI;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ecommerce")
@RequiredArgsConstructor
public class DemoController {

    //private final EcommerceServiceI ecommerceServiceI;

    @GetMapping("/{id}")
    public ResponseEntity<EcommerceResponseDto> getUserById(@PathVariable(required = true) String id){

        return null;
    }

}
