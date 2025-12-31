package com.example.assignment.task;

import jakarta.validation.constraints.NotBlank;

public record TaskRequestDTO(
        @NotBlank String title,
        String description
) {}
