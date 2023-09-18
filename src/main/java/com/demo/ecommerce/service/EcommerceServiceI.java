package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.EcommerceResponseDto;

import java.time.LocalDateTime;

public interface EcommerceServiceI {

    EcommerceResponseDto findProductPrices(LocalDateTime date, int productId, int brandId);

}
