package com.demo.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BRANDS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BrandEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

}
