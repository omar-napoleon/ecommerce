package com.demo.ecommerce.unit.controller;

import com.demo.ecommerce.controller.DemoController;
import com.demo.ecommerce.dto.EcommerceResponseDto;
import com.demo.ecommerce.dto.ExceptionResponseDto;
import com.demo.ecommerce.service.EcommerceServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.MissingRequestValueException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static com.demo.ecommerce.helper.JsonHelper.jsonToObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DemoController.class)
class DemoControllerTest {

    private final MockMvc mock;

    private final ObjectMapper objectMapper;

    @MockBean
    private EcommerceServiceI ecommerceServiceI;

    @Autowired
    public DemoControllerTest(MockMvc mock, ObjectMapper objectMapper){
        this.mock = mock;
        this.objectMapper = objectMapper;
    }

    @Test
    void findPriceSuccess() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        EcommerceResponseDto expectedResponse = EcommerceResponseDto.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .brandId(1)
                .productId(35455)
                .price(new BigDecimal(30.00))
                .fee(new BigDecimal(20.00))
                .curr("EUR")
                .build();
        when(ecommerceServiceI.findProductPrices(date, 35455, 1)).thenReturn(expectedResponse);

        var result = mock
                .perform(get("/api/v1/ecommerce/product/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andReturn();

        var response = jsonToObject(objectMapper, result.getResponse().getContentAsString(), EcommerceResponseDto.class);
        assertNotNull(response.getPrice());
        assertNotNull(response.getFee());
        assertNotNull(response.getProductId());
        assertNotNull(response.getBrandId());
        assertNotNull(response.getStartDate());
        assertNotNull(response.getEndDate());
        assertNotNull(response.getCurr());

    }

    @Test
    void findPriceNotFound() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        ExceptionResponseDto expectedResponse = ExceptionResponseDto.builder()
                .error("Not Found")
                .message("No value present")
                .build();
        when(ecommerceServiceI.findProductPrices(date, 2, 2)).thenThrow(new NoSuchElementException());

        var result = mock
                .perform(get("/api/v1/ecommerce/product/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "2")
                        .param("brandId", "2"))
                .andExpect(status().isNotFound()).andReturn();
        var response = jsonToObject(objectMapper, result.getResponse().getContentAsString(), ExceptionResponseDto.class);
        assertEquals(expectedResponse.getError(), response.getError());
    }

    @Test
    void findPriceInvalidRequest() throws Exception {
        ExceptionResponseDto expectedResponse = ExceptionResponseDto.builder()
                .error("Bad Request")
                .message("Required request parameter 'date' for method parameter type LocalDateTime is not present")
                .build();
        when(ecommerceServiceI.findProductPrices(null, 2, 2)).thenThrow(MissingRequestValueException.class);

        var result = mock
                .perform(get("/api/v1/ecommerce/product/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("productId", "2")
                        .param("brandId", "2"))
                .andExpect(status().isBadRequest()).andReturn();
        var response = jsonToObject(objectMapper, result.getResponse().getContentAsString(), ExceptionResponseDto.class);
        assertEquals(expectedResponse.getError(), response.getError());
    }
}
