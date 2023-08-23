package com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto;

import org.springframework.security.core.userdetails.UserDetails;

public record AuthenticationResponseDto(
        String token,
        UserDetails user
) {
}
