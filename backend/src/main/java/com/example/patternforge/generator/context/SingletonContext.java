package com.example.patternforge.generator.context;

import jakarta.validation.constraints.NotBlank;

public record SingletonContext(
        @NotBlank String className) implements CodeGenerationContext {
}
