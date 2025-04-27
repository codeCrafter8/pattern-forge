package com.example.patternforge.service;

import com.example.patternforge.factory.CodeGeneratorFactory;
import com.example.patternforge.generator.GeneratedFile;
import com.example.patternforge.generator.PatternGenerator;
import com.example.patternforge.generator.context.CodeGenerationContext;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CodeGeneratorService {
    private final CodeGeneratorFactory codeGeneratorFactory;

    private static final String CONTEXT_REGEX = "(?i)context";

    public List<GeneratedFile> generateFiles(
            CodeGenerationContext context) throws IOException, TemplateException {

        String pattern = context.getClass().getSimpleName().replaceAll(CONTEXT_REGEX, "");

        PatternGenerator generator = codeGeneratorFactory.getGenerator(pattern);
        generator.setContext(context);

        return generator.generateFiles();
    }
}
