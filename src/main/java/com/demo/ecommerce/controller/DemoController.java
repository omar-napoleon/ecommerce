package com.demo.ecommerce.controller;

import com.demo.ecommerce.dto.EcommerceResponseDto;
import com.demo.ecommerce.dto.ExceptionResponseDto;
import com.demo.ecommerce.service.EcommerceServiceI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Find the price of a product on a given date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the price product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EcommerceResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameters",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponseDto.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponseDto.class)) }
            ) })
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
