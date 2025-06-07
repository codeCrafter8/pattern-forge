package com.example.patternforge.service.pattern.adapter;

import com.example.patternforge.service.pattern.PatternContext;
import jakarta.validation.constraints.NotBlank;

public record AdapterContext(
        @NotBlank String clientClassName,
        @NotBlank String adapterClassName,
        @NotBlank String targetInterfaceName,
        @NotBlank String adapteeClassName,
        @NotBlank String adapteeMethodName,
        @NotBlank String language) implements PatternContext {

    @Override
    public String getLanguage() {
        return language;
    }

}
