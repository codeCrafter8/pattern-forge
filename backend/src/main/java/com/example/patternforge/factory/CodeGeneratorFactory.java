package com.example.patternforge.factory;

import com.example.patternforge.generator.PatternGenerator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CodeGeneratorFactory {
    private final Map<String, PatternGenerator> generators;

    public CodeGeneratorFactory(List<PatternGenerator> patternGenerators) {
        this.generators = patternGenerators.stream()
                .collect(Collectors.toMap(PatternGenerator::getName, Function.identity()));
    }

    public PatternGenerator getGenerator(String patternName) {
        PatternGenerator generator = generators.get(patternName.toLowerCase());

        if (generator == null) {
            throw new IllegalArgumentException(String.format("Design pattern %s not supported.", patternName));
        }

        return generator;
    }
}
