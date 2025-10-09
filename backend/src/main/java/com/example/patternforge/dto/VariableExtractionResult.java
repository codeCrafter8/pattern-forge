package com.example.patternforge.dto;

import java.util.List;
import java.util.Set;

public record VariableExtractionResult(
        Set<String> singleVariables,
        Set<String> repeatableVariables,
        List<VariableGroup> groupedVariables,
        Set<String> booleanVariables
) {
}
