package com.pblgllgs.securitysb3ss6jwtflywaybra.service;

import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto.AuthenticationResponseDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto.RegistrationRequestDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.auth.dto.RegistrationResponseDto;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.user.User;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.user.mapper.UserMapper;
import com.pblgllgs.securitysb3ss6jwtflywaybra.exception.UserAlreadyExistsException;
import com.pblgllgs.securitysb3ss6jwtflywaybra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthorizationService authorizationService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthenticationResponseDto authenticate(String username,String password){
        UserDetails userDetails = authorizationService.loadUserByUsername(username);
        if (userDetails == null){
            throw new UsernameNotFoundException("User not found");
        }
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        return new AuthenticationResponseDto(
                tokenService.generateToken((User) auth.getPrincipal()),
                userDetails
        );
    }

    public RegistrationResponseDto register(RegistrationRequestDto registrationRequestDto){
        Optional<UserDetails> userDetails = userRepository.findByUsername(registrationRequestDto.name());
        if (userDetails.isPresent()){
            throw new UserAlreadyExistsException("User Already exists");
        }

        User user = UserMapper.INSTANCE.toEntity(registrationRequestDto);
        user.setPassword(passwordEncoder.encode(registrationRequestDto.password()));
        User userSaved = userRepository.save(user);
        return new RegistrationResponseDto(
                userSaved
        );
    }
}
