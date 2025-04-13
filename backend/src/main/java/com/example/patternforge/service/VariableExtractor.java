package com.example.patternforge.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class VariableExtractor {

    private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\$\\{([a-zA-Z0-9_.]+)}");

    public Set<String> extractVariables(File[] templateFiles) throws IOException {
        Set<String> variables = new HashSet<>();

        for (File file : templateFiles) {
            String content = readFileContent(file.toPath());
            variables.addAll(extractVariables(content));
        }

        return variables;
    }

    private String readFileContent(Path path) throws IOException {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            log.error("Cannot read file: {}", path, e);
            throw e;
        }
    }

    private Set<String> extractVariables(String content) {
        Matcher matcher = VARIABLE_PATTERN.matcher(content);
        Set<String> variables = new HashSet<>();

        while (matcher.find()) {
            variables.add(matcher.group(1));
        }

        return variables;
    }

}
