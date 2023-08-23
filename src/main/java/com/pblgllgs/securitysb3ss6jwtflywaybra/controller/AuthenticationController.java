package com.pblgllgs.securitysb3ss6jwtflywaybra.controller;

import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto.AuthenticationRequestDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto.AuthenticationResponseDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto.RegistrationRequestDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto.RegistrationResponseDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return new ResponseEntity<>(
                authenticationService.authenticate(authenticationRequestDto.username(), authenticationRequestDto.password()),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> register(@Valid @RequestBody RegistrationRequestDto registrationRequestDto) {
        return new ResponseEntity<>(authenticationService.register(registrationRequestDto), HttpStatus.CREATED);
    }
}
