package com.example.patternforge.service;

import com.example.patternforge.dto.VariableExtractionResult;
import com.example.patternforge.exception.PatternProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Service
public class PatternConfigService {

    private final TemplateReader templateReader;
    private final VariableExtractor variableExtractor;

    public VariableExtractionResult getPatternConfig(String patternName) {
        try {
            log.info("Looking for variables for pattern: {}", patternName);

            File[] templateFiles = templateReader.getTemplateFiles(patternName);

            VariableExtractionResult variables = variableExtractor.extractVariables(templateFiles);

            log.info("Found variables {} for pattern: {}", variables, patternName);

            return variables;

        } catch (IOException e) {
            log.error("Error reading templates for pattern: {}", patternName, e);
            throw new PatternProcessingException(String.format("Error processing pattern: %s", patternName), e);
        }
    }
}
