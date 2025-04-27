package com.example.patternforge.generator.context;

import jakarta.validation.constraints.NotBlank;

public record AdapterContext(
        @NotBlank String clientClassName,
        @NotBlank String adapterClassName,
        @NotBlank String targetInterfaceName,
        @NotBlank String adapteeClassName,
        @NotBlank String adapteeMethodName) implements CodeGenerationContext {
}
