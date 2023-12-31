package com.demo.ecommerce.cucumber.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {

    public static <T> String objectToJson(ObjectMapper objectMapper, T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T jsonToObject(ObjectMapper objectMapper, String payload, Class<T> clazz)
            throws JsonProcessingException {
        return objectMapper.readValue(payload, clazz);
    }

    public static <T> T jsonToObject(ObjectMapper objectMapper, String payload, TypeReference<T> clazz)
            throws JsonProcessingException {
        return objectMapper.readValue(payload, clazz);
    }
}
