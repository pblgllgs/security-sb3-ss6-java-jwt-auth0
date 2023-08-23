package com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDto(@NotBlank String name,@NotNull BigDecimal price) {
}
