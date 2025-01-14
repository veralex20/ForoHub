package com.foro.ForoHub.controller;

import com.foro.ForoHub.dto.RegisterRequest;
import com.foro.ForoHub.entity.AuthData;
import com.foro.ForoHub.entity.User;
import com.foro.ForoHub.repository.AuthDataRepository;
import com.foro.ForoHub.repository.UserRepository;
import com.foro.ForoHub.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthDataRepository authDataRepository;

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public String login(@RequestBody RegisterRequest registerRequest) {
        // Autenticar al usuario
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            registerRequest.getUsername(),
                            registerRequest.getPassword()
                    )
            );

            // Generar un token JWT
            String token = tokenService.generateToken(registerRequest.getUsername());
            return "Bearer " + token;
        }catch (Exception e) {
            e.printStackTrace();
            return "Authentication failed: " + e.getMessage();
        }
    }

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        // Crear y guardar el usuario
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        userRepository.save(user);

        // Crear y guardar los datos de autenticación
        AuthData authData = new AuthData();
        authData.setUsername(request.getUsername());
        authData.setPassword(passwordEncoder.encode(request.getPassword()));
        authData.setUser(user);
        authDataRepository.save(authData);

        return "Usuario registrado exitosamente.";
    }
}
