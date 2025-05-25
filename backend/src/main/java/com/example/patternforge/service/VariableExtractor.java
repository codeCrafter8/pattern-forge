package com.example.patternforge.service;

import com.example.patternforge.dto.VariableExtractionResult;
import com.example.patternforge.dto.VariableGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class VariableExtractor {

    private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\$\\{([a-zA-Z0-9_.]+)}");
    private static final List<VariableGroup> PREDEFINED_GROUPS = List.of(
            new VariableGroup("productVariants", Set.of("concreteCreatorClassName", "productClassName"))
    );

    public VariableExtractionResult extractVariables(File[] templateFiles) throws IOException {
        Set<String> allVariables = extractAllVariables(templateFiles);

        return processVariableGroups(allVariables);
    }

    private Set<String> extractAllVariables(File[] templateFiles) throws IOException {
        Set<String> allVariables = new HashSet<>();

        for (File file : templateFiles) {
            String content = readFileContent(file.toPath());
            allVariables.addAll(extractVariablesFromContent(content));
        }

        return allVariables;
    }

    private String readFileContent(Path path) throws IOException {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            log.error("Cannot read file: {}", path, e);
            throw e;
        }
    }

    private Set<String> extractVariablesFromContent(String content) {
        Matcher matcher = VARIABLE_PATTERN.matcher(content);
        Set<String> variables = new HashSet<>();

        while (matcher.find()) {
            variables.add(matcher.group(1));
        }

        return variables;
    }

    private VariableExtractionResult processVariableGroups(Set<String> allVariables) {
        List<VariableGroup> matchedGroups = new ArrayList<>();

        for (VariableGroup group : PREDEFINED_GROUPS) {
            if (allVariables.containsAll(group.variables())) {
                allVariables.removeAll(group.variables());
                matchedGroups.add(group);
            }
        }

        return new VariableExtractionResult(allVariables, matchedGroups);
    }

}
