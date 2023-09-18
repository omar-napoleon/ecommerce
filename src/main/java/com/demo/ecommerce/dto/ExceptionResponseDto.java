package com.demo.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponseDto implements Serializable {

    private String error;

    @JsonProperty("message")
    private String message;

}
