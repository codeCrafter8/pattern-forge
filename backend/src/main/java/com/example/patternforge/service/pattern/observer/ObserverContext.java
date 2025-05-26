package com.example.patternforge.service.pattern.observer;

import com.example.patternforge.service.pattern.PatternContext;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ObserverContext(
        @NotBlank String subjectInterfaceName,
        @NotBlank String observerInterfaceName,
        @NotBlank String updateMethodName,
        @NotBlank String concreteSubjectClassName,
        @NotEmpty List<String> concreteObserverClassName
) implements PatternContext {
}
