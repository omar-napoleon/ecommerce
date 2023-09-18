package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.EcommerceResponseDto;
import com.demo.ecommerce.entity.PricesEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

/**
 * Mapper
 */
@Service
public class EcommerceMapper extends ModelMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public EcommerceMapper() {
        this.modelMapper.addConverter(new StringToLocalDateTimeConverter());
        TypeMap<PricesEntity, EcommerceResponseDto> propertyMapper =
                this.modelMapper.createTypeMap(PricesEntity.class, EcommerceResponseDto.class);
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> src.getFee().getPrice(), EcommerceResponseDto::setFee)
        );
    }

    public EcommerceResponseDto pricesEntityToRespDto(PricesEntity entity) {
        return this.modelMapper.map(entity, EcommerceResponseDto.class);
    }
}
