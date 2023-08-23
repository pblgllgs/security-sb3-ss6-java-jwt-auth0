package com.pblgllgs.securitysb3ss6jwtflywaybra.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    USER("user"), ADMIN("admin");
    private final String role;
}
