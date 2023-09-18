package com.demo.ecommerce.unit.service;


import com.demo.ecommerce.dto.EcommerceResponseDto;
import com.demo.ecommerce.entity.BrandEntity;
import com.demo.ecommerce.entity.FeesEntity;
import com.demo.ecommerce.entity.PricesEntity;
import com.demo.ecommerce.mapper.EcommerceMapper;
import com.demo.ecommerce.repository.PricesRepository;
import com.demo.ecommerce.service.EcommerceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@WebMvcTest(EcommerceServiceImpl.class)
class EcommerceServiceImplTest {

    @MockBean
    private PricesRepository pricesRepository;

    @MockBean
    private EcommerceMapper ecommerceMapper;

    @InjectMocks
    private EcommerceServiceImpl ecommerceService;

    private PricesEntity pricesEntity = new PricesEntity();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ecommerceService = new EcommerceServiceImpl(pricesRepository, ecommerceMapper);
        pricesEntity = PricesEntity.builder()
                .startDate("2020-06-14-00.00.00")
                .endDate("2020-12-31-23.59.59")
                .fee(new FeesEntity(1, new BigDecimal(14.25), "EUR"))
                .price(new BigDecimal(20.00))
                .curr("EUR")
                .priority(1)
                .id(1)
                .brand(new BrandEntity(1, "ZARA"))
                .productId(1)
                .build();

    }

    @Test
    void testFindProductPrices() {
        // Datos de prueba
        LocalDateTime date = LocalDateTime.of(2023, 9, 15, 12, 0, 10);
        int productId = 1;
        int brandId = 2;
        BigDecimal price = new BigDecimal("10.00");

        // Crear un PricesEntity de prueba
        pricesEntity.setPrice(price);
        when(pricesRepository.findPricesByProductIdAndBrandIdAndDate(productId, brandId, date)).thenReturn(Optional.of(pricesEntity));

        // Crear un EcommerceResponseDto de prueba
        EcommerceResponseDto responseDto = new EcommerceResponseDto();
        responseDto.setPrice(price);

        when(ecommerceMapper.pricesEntityToRespDto(pricesEntity))
                .thenReturn(responseDto);
        EcommerceResponseDto result = ecommerceService.findProductPrices(date, productId, brandId);


        assertEquals(price, result.getPrice());
    }

    @Test
    void testFindProductPricesWhenOptionalIsEmpty() {
        LocalDateTime date = LocalDateTime.of(2023, 9, 15, 12, 0);
        int productId = 1;
        int brandId = 2;
        when(pricesRepository.findPricesByProductIdAndBrandIdAndDate(productId, brandId, date)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            ecommerceService.findProductPrices(date, productId, brandId);
        });

        // Verificar que la excepción lanzada sea la esperada (en este caso, NoSuchElementException).
        assertEquals("No value present", exception.getMessage());
    }
}
