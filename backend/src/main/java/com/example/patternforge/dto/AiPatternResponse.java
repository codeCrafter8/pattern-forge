package com.example.patternforge.dto;

import java.util.List;

public record AiPatternResponse(
        List<GeneratedFile> generatedFiles,
        String patternName
) {}
