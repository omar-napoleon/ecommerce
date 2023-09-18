package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.EcommerceResponseDto;
import com.demo.ecommerce.mapper.EcommerceMapper;
import com.demo.ecommerce.repository.PricesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EcommerceServiceImpl implements EcommerceServiceI {

    private final PricesRepository pricesRepository;

    private final EcommerceMapper ecommerceMapper;

    public EcommerceServiceImpl(PricesRepository pricesRepository, EcommerceMapper ecommerceMapper) {
        this.pricesRepository = pricesRepository;
        this.ecommerceMapper = ecommerceMapper;
    }

    @Override
    public EcommerceResponseDto findProductPrices(LocalDateTime date, int productId, int brandId) {
        var pricesEntity = pricesRepository.findPricesByProductIdAndBrandIdAndDate(productId, brandId, date)
                .orElseThrow();
        return ecommerceMapper.pricesEntityToRespDto(pricesEntity);
    }

}
