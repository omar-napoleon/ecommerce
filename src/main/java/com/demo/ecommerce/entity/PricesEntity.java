package com.demo.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="PRICES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PricesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "id", nullable = false)
    private BrandEntity brand;

    @Column(name = "START_DATE", nullable = false)
    private String startDate;

    @Column(name = "END_DATE", nullable = false)
    private String endDate;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Integer productId;

    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;

    @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "FEE_ID", referencedColumnName = "id", nullable = false)
    private FeesEntity fee;

    @Column(name = "CURR", nullable = false, length = 3)
    private String curr;


}