package com.camilo.demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtService {

    private static final String SECRET_KEY =
            "MI_CLAVE_SUPER_SECRETA_DE_32_CARACTERES";

    public String generarToken(String username, String rol) {

        return Jwts.builder()
                .setSubject(username)
                .claim("role", rol)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS256)
                .compact();
    }

    public String extraerUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(
                        SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean esTokenValido(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(
                            SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extraerRol(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
                        )
                )
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }


}
