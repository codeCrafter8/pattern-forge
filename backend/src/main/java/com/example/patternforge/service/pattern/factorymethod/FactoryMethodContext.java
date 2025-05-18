package com.example.patternforge.service.pattern.factorymethod;

import com.example.patternforge.service.pattern.PatternContext;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record FactoryMethodContext(
        @NotBlank String productInterfaceName,
        @NotBlank String productMethodName,
        @NotBlank String creatorClassName,
        @NotBlank String creatorMethodName,
        @NotEmpty List<ProductVariant> productVariants) implements PatternContext {

    public record ProductVariant(
            @NotBlank String productClassName,
            @NotBlank String concreteCreatorClassName
    ) {
    }
}
