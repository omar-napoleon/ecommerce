package com.demo.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "FEES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FeesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "CURR", nullable = false, length = 3)
    private String curr;
}
