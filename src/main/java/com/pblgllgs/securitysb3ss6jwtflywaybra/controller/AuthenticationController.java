package com.pblgllgs.securitysb3ss6jwtflywaybra.controller;

import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto.AuthenticationRequestDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto.AuthenticationResponseDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto.RegistrationRequestDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto.RegistrationResponseDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.user.User;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.user.mapper.UserMapper;
import com.pblgllgs.securitysb3ss6jwtflywaybra.exception.UserAlreadyExistsException;
import com.pblgllgs.securitysb3ss6jwtflywaybra.repository.UserRepository;
import com.pblgllgs.securitysb3ss6jwtflywaybra.service.AuthorizationService;
import com.pblgllgs.securitysb3ss6jwtflywaybra.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthorizationService authorizationService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) {
        UserDetails userDetails = authorizationService.loadUserByUsername(authenticationRequestDto.username());
        if (userDetails == null){
            throw new UsernameNotFoundException("User not found");
        }
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(authenticationRequestDto.username(), authenticationRequestDto.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        AuthenticationResponseDto authenticationResponseDto  = new AuthenticationResponseDto(
                tokenService.generateToken((User) auth.getPrincipal()),
                userDetails
        );
        return new ResponseEntity<>(authenticationResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> register(@Valid @RequestBody RegistrationRequestDto registrationRequestDto) {
        Optional<UserDetails> userDetails = userRepository.findByUsername(registrationRequestDto.name());
        if (userDetails.isPresent()){
            throw new UserAlreadyExistsException("User Already exists");
        }

        User user = UserMapper.INSTANCE.toEntity(registrationRequestDto);
        user.setPassword(passwordEncoder.encode(registrationRequestDto.password()));
        User userSaved = userRepository.save(user);
        RegistrationResponseDto authenticationResponseDto  = new RegistrationResponseDto(
                userSaved
        );
        return new ResponseEntity<>(authenticationResponseDto, HttpStatus.CREATED);
    }
}
