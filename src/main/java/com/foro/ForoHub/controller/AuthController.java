package com.foro.ForoHub.controller;

import com.foro.ForoHub.dto.RegisterRequest;
import com.foro.ForoHub.entity.AuthData;
import com.foro.ForoHub.entity.User;
import com.foro.ForoHub.repository.AuthDataRepository;
import com.foro.ForoHub.repository.UserRepository;
import com.foro.ForoHub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid RegisterRequest registerRequest) {
        try {
            // Intentar autenticar al usuario con las credenciales proporcionadas
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                            registerRequest.getUsername(),
                            registerRequest.getPassword());

            // Si la autenticación es exitosa, generar el token JWT
            String token = tokenService.generateToken(registerRequest.getUsername());

            // Crear el mapa con el token
            Map<String, String> response = new HashMap<>();
            response.put("token", "Bearer " + token);

            // Retornar el token en formato JSON
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // En caso de error, imprimir el stack trace y retornar un mensaje adecuado
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Credenciales incorrectas o error en la autenticación");
            return ResponseEntity.badRequest().body(errorResponse);
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
