package com.example.assignment.task;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        String ownerEmail
) {}
