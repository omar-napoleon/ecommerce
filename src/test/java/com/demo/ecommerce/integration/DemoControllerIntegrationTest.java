package com.demo.ecommerce.integration;

import com.demo.ecommerce.dto.EcommerceResponseDto;
import com.demo.ecommerce.dto.ExceptionResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.demo.ecommerce.cucumber.helper.JsonHelper.jsonToObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class DemoControllerIntegrationTest {

    private final MockMvc mock;
    private final ObjectMapper objectMapper;

    private static final String URL_TEMPLATE = "/api/v1/ecommerce/product/price";


    @Autowired
    public DemoControllerIntegrationTest(MockMvc mock, ObjectMapper objectMapper) {
        this.mock = mock;
        this.objectMapper = objectMapper;
    }

    @Test
    void testSuccess() throws Exception {
        EcommerceResponseDto expectedResponse = EcommerceResponseDto.builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .brandId(1)
                .productId(35455)
                .price(new BigDecimal(35.50).setScale(2))
                .fee(new BigDecimal(20.00))
                .curr("EUR")
                .build();

        var result = mock
                .perform(get(URL_TEMPLATE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andReturn();

        var response = jsonToObject(objectMapper, result.getResponse().getContentAsString(), EcommerceResponseDto.class);
        assertEquals(response.getPrice(), expectedResponse.getPrice());
    }

    @Test
    void notFound() throws Exception {
        ExceptionResponseDto expectedResponse = ExceptionResponseDto.builder()
                .error("Not Found")
                .message("No value present")
                .build();

        var result = mock
                .perform(get(URL_TEMPLATE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "2")
                        .param("brandId", "2"))
                .andExpect(status().isNotFound()).andReturn();
        var response = jsonToObject(objectMapper, result.getResponse().getContentAsString(), ExceptionResponseDto.class);
        assertEquals(expectedResponse.getError(), response.getError());
    }

    @Test
    void invalidRequest() throws Exception {
        ExceptionResponseDto expectedResponse = ExceptionResponseDto.builder()
                .error("Bad Request")
                .message("Required request parameter 'date' for method parameter type LocalDateTime is not present")
                .build();

        var result = mock
                .perform(get(URL_TEMPLATE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("productId", "2")
                        .param("brandId", "2"))
                .andExpect(status().isBadRequest()).andReturn();
        var response = jsonToObject(objectMapper, result.getResponse().getContentAsString(), ExceptionResponseDto.class);
        assertEquals(expectedResponse.getError(), response.getError());
    }
}
