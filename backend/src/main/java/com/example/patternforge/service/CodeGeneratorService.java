package com.example.patternforge.service;

import com.example.patternforge.factory.CodeGeneratorFactory;
import com.example.patternforge.generator.CodeGenerationContext;
import com.example.patternforge.generator.PatternGenerator;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class CodeGeneratorService {
    private final CodeGeneratorFactory codeGeneratorFactory;

    public String generateCode(
            String pattern,
            CodeGenerationContext context) throws TemplateException, IOException {
        PatternGenerator generator = codeGeneratorFactory.getGenerator(pattern);

        generator.setContext(context);

        return generator.generateCode();
    }
}
