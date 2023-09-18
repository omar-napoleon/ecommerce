package com.demo.ecommerce.controller;

import com.demo.ecommerce.dto.EcommerceResponseDto;
import com.demo.ecommerce.service.EcommerceServiceI;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/ecommerce")
@RequiredArgsConstructor
public class DemoController {

    private final EcommerceServiceI ecommerceServiceI;

    @GetMapping("/product/price")
    public ResponseEntity<EcommerceResponseDto> getPricing(
            @RequestParam(name = "date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime date,
            @RequestParam(name = "productId")
            @Min(1) int productId,
            @RequestParam(name = "brandId")
            @Min(1) int brandId){

        return ResponseEntity.ok(ecommerceServiceI.findProductPrices(date, productId, brandId));
    }

}
