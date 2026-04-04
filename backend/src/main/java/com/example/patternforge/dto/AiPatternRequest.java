package com.example.patternforge.dto;

import jakarta.validation.constraints.NotBlank;

public record AiPatternRequest(
        @NotBlank String problemDescription
) {}
