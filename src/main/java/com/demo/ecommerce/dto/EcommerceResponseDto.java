package com.demo.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EcommerceResponseDto implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String productId;

    private String brandId;
    private String brandName;
    private Instant createdAt;
    private Instant modifiedAt;
    private Instant lastLogin;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    @JsonProperty("isActive")
    private boolean active;

}
