package com.mycompany.citasmedicas.controller;

import com.mycompany.citasmedicas.dto.LoginRequest;
import com.mycompany.citasmedicas.dto.LoginResponse;
import com.mycompany.citasmedicas.model.Usuario;
import com.mycompany.citasmedicas.repository.UsuarioRepository;
import com.mycompany.citasmedicas.security.JwtUtil;
import com.mycompany.citasmedicas.mapper.LoginMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final LoginMapper loginMapper;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UsuarioRepository usuarioRepository,
                          LoginMapper loginMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
        this.loginMapper = loginMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        String correo = request.getCorreo().trim();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(correo, request.getPassword())
        );

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new com.mycompany.citasmedicas.exception.ResourceNotFoundException(
                        "Usuario no encontrado"));

        org.springframework.security.core.userdetails.UserDetails userDetails =
                org.springframework.security.core.userdetails.User
                        .withUsername(usuario.getCorreo())
                        .password(usuario.getPassword())
                        .authorities(usuario.getRoles().stream()
                                .map(r -> r.getNombre())
                                .toArray(String[]::new))
                        .build();

        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(loginMapper.toResponse(usuario, token));
    }

    @GetMapping("/me")
    public ResponseEntity<LoginResponse> me(Authentication authentication) {
        Usuario usuario = usuarioRepository.findByCorreo(authentication.getName())
                .orElseThrow(() -> new com.mycompany.citasmedicas.exception.ResourceNotFoundException(
                        "Usuario no encontrado"));

        return ResponseEntity.ok(loginMapper.toResponse(usuario, null));
    }
}
