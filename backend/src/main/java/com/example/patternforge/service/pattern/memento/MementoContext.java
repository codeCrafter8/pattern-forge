package com.example.patternforge.service.pattern.memento;

import com.example.patternforge.service.pattern.PatternContext;
import jakarta.validation.constraints.NotBlank;

public record MementoContext(
        @NotBlank String originatorClassName,
        @NotBlank String mementoClassName,
        @NotBlank String caretakerClassName,
        @NotBlank String stateFieldName,
        @NotBlank String language,
        boolean undoEnabled) implements PatternContext {

    @Override
    public String getLanguage() {
        return language;
    }
}
