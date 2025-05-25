package com.example.patternforge.dto;

import java.util.List;
import java.util.Set;

public record VariableExtractionResult(
        Set<String> singleVariables,
        List<VariableGroup> groupedVariables
) {
}
