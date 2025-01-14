package com.foro.ForoHub.service;

import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Service
public class TokenService {
    // Esta clase se encargará de generar y validar los tokens JWT.

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // Método para generar un token JWT
    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(Algorithm.HMAC256(secret));
    }

    // Método para validar un token JWT
    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            return null; // Token inválido
        }
    }
}
