package com.example.patternforge.service;

import com.example.patternforge.factory.CodeGeneratorFactory;
import com.example.patternforge.generator.PatternGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CodeGeneratorService {
    private final CodeGeneratorFactory codeGeneratorFactory;

    public String generateCode(String pattern) {
        PatternGenerator generator = codeGeneratorFactory.getGenerator(pattern);
        return generator.generateCode();
    }
}
