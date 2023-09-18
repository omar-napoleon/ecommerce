package com.demo.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EcommerceResponseDto implements Serializable {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer productId;

    private Integer brandId;

    private BigDecimal price;

    private BigDecimal fee;

    private String curr;

}
