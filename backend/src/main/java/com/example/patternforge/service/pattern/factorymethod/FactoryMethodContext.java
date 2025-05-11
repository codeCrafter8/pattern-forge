package com.example.patternforge.service.pattern.factorymethod;

import com.example.patternforge.service.pattern.PatternContext;
import jakarta.validation.constraints.NotBlank;

public record FactoryMethodContext(
        @NotBlank String productInterfaceName,
        @NotBlank String productMethodName,
        @NotBlank String productClassName,
        @NotBlank String creatorClassName,
        @NotBlank String creatorMethodName,
        @NotBlank String concreteCreatorClassName) implements PatternContext {
}
