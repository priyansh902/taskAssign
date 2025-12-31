package com.example.assignment.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.assignment.user.Role;
import com.example.assignment.user.User;
import com.example.assignment.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByEmail("admin@system.com")) {
            User admin = new User();
            admin.setEmail("admin@system.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ROLE_ADMIN);
            userRepository.save(admin);
        }
    }
}
