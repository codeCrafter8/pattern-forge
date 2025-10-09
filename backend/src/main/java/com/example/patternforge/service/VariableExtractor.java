package com.example.patternforge.service;

import com.example.patternforge.dto.VariableExtractionResult;
import com.example.patternforge.dto.VariableGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class VariableExtractor {

    private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\$\\{([a-zA-Z0-9_.]+)}");
    private static final Pattern IF_PATTERN = Pattern.compile("<#(if|elseif)\\s+([a-zA-Z0-9_.]+)>");

    private static final Map<String, List<String>> PREDEFINED_REPEATABLES = Map.of(
            "observer", List.of("concreteObserverClassName"));

    private static final Map<String, List<VariableGroup>> PREDEFINED_GROUPS = Map.of(
            "factory method",
            List.of(new VariableGroup("productVariants", Set.of("concreteCreatorClassName", "productClassName"))));

    private static final Map<String, List<String>> PREDEFINED_BOOLEANS = Map.of(
            "memento", List.of("undoEnabled")
    );


    public VariableExtractionResult extractVariables(File[] templateFiles, String patternName) throws IOException {
        Set<String> allVariables = extractAllVariables(templateFiles);

        return processVariables(allVariables, patternName);
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
        Set<String> variables = new HashSet<>();

        Matcher variableMatcher = VARIABLE_PATTERN.matcher(content);
        while (variableMatcher.find()) {
            variables.add(variableMatcher.group(1));
        }

        Matcher ifMatcher = IF_PATTERN.matcher(content);
        while (ifMatcher.find()) {
            variables.add(ifMatcher.group(2));
        }

        return variables;
    }

    private VariableExtractionResult processVariables(Set<String> allVariables, String patternName) {
        List<VariableGroup> matchedGroups = new ArrayList<>();
        Set<String> matchedRepeatables = new HashSet<>();
        Set<String> matchedBooleans = new HashSet<>();

        List<VariableGroup> groups = PREDEFINED_GROUPS.get(patternName.toLowerCase());
        if (groups != null) {
            for (VariableGroup group : groups) {
                if (allVariables.containsAll(group.variables())) {
                    allVariables.removeAll(group.variables());
                    matchedGroups.add(group);
                }
            }
        }

        List<String> repeatables = PREDEFINED_REPEATABLES.get(patternName.toLowerCase());
        if (repeatables != null) {
            for (String repeatable : repeatables) {
                if (allVariables.contains(repeatable)) {
                    allVariables.remove(repeatable);
                    matchedRepeatables.add(repeatable);
                }
            }
        }

        List<String> booleanVars = PREDEFINED_BOOLEANS.get(patternName.toLowerCase());
        if (booleanVars != null) {
            for (String boolVar : booleanVars) {
                if (allVariables.contains(boolVar)) {
                    allVariables.remove(boolVar);
                    matchedBooleans.add(boolVar);
                }
            }
        }

        return new VariableExtractionResult(
                allVariables,
                matchedRepeatables,
                matchedGroups,
                matchedBooleans);
    }

}
