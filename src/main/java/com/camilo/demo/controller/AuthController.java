package com.camilo.demo.controller;

import com.camilo.demo.model.AuthUser;
import com.camilo.demo.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthUser user) {

        // Validación temporal (luego será con BD)
        if (!"camilo".equals(user.getUsername()) ||
                !"1234".equals(user.getPassword())) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        }

        String rol = "ROLE_USER";

        String token = jwtService.generarToken(
                user.getUsername(), rol
        );
        return ResponseEntity.ok(token);
    }
}
