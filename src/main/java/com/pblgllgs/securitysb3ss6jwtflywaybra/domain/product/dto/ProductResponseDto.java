package com.pblgllgs.securitysb3ss6jwtflywaybra.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDto(@NotBlank UUID id, @NotBlank String name, @NotNull BigDecimal price) {
}
