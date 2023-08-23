package com.pblgllgs.securitysb3ss6jwtflywaybra.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pblgllgs.securitysb3ss6jwtflywaybra.domain.user.User;
import com.pblgllgs.securitysb3ss6jwtflywaybra.utils.RSAKeyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
@RequiredArgsConstructor
public class TokenService {

    private final RSAKeyProperties keys;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.RSA256(keys.getPublicKey(), keys.getPrivateKey());
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(genExpitarionInstant())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.RSA256(keys.getPublicKey(), keys.getPrivateKey());
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant genExpitarionInstant(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
