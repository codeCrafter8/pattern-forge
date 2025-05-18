package com.example.patternforge.service.pattern.singleton;

import com.example.patternforge.service.pattern.PatternContext;
import jakarta.validation.constraints.NotBlank;

public record SingletonContext(
        @NotBlank String className) implements PatternContext {
}
