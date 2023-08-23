package com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto;

import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.user.enums.UserRole;
import jakarta.validation.constraints.NotBlank;

public record RegistrationRequestDto(
        @NotBlank String name,
        @NotBlank String password,
        UserRole role
) {
}
