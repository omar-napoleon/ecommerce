package com.demo.ecommerce.unit.mapper;

import com.demo.ecommerce.dto.EcommerceResponseDto;
import com.demo.ecommerce.entity.FeesEntity;
import com.demo.ecommerce.entity.PricesEntity;
import com.demo.ecommerce.mapper.EcommerceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(EcommerceMapper.class)
class EcommerceMapperTest {

    @Autowired
    private EcommerceMapper modelMapper;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    @BeforeEach
    void setUp() {
    }

    @Test
    void testPricesEntityToRespDto() {
        LocalDateTime expected = LocalDateTime.parse("2020-06-14-00.00.00", formatter);
        PricesEntity pricesEntity = PricesEntity.builder()
                .startDate("2020-06-14-00.00.00")
                .endDate("2020-12-31-23.59.59")
                .fee(new FeesEntity(1, new BigDecimal(14.25), "EUR"))
                .price(new BigDecimal(20.00))
                .build();
        EcommerceResponseDto responseDto = modelMapper.pricesEntityToRespDto(pricesEntity);
        assertEquals(pricesEntity.getPrice(), responseDto.getPrice());
        assertEquals(pricesEntity.getFee().getPrice(), responseDto.getFee());
        assertEquals(expected, responseDto.getStartDate());
    }
}

