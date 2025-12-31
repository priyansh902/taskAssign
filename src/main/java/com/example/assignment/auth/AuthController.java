package com.example.assignment.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.security.JwtUtil;
import com.example.assignment.user.Role;
import com.example.assignment.user.User;
import com.example.assignment.user.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        User user = new User();
        user.setName(req.name());
        user.setEmail(req.email());
        user.setPassword(encoder.encode(req.password()));
        user.setRole(Role.ROLE_USER);
        if (userRepository.existsByEmail(req.email())) {
            throw new RuntimeException("Email already registered");
        }
        userRepository.save(user);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password()));

        User user = userRepository.findByEmail(req.email()).orElseThrow();
        String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(token, user.getRole().name()));
    }
}

