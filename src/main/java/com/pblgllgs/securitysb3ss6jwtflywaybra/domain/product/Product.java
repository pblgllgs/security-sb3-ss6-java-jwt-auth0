package com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "Product")
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(name = "name_UK", columnNames = "name")})
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private BigDecimal price;
}