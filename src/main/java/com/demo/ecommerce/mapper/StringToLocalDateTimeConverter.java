package com.demo.ecommerce.mapper;
import org.modelmapper.AbstractConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTimeConverter extends AbstractConverter<String, LocalDateTime> {

    @Override
    protected LocalDateTime convert(String source) {
        if (source == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        return LocalDateTime.parse(source, formatter);
    }
}
