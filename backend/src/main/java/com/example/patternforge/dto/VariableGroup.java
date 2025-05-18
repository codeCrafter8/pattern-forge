package com.example.patternforge.dto;

import java.util.Set;

public record VariableGroup(
        String groupName,
        Set<String> variables
) {
}
