package com.example.patternforge.service;

import com.example.patternforge.dto.GeneratedFile;
import com.example.patternforge.service.pattern.PatternContext;
import com.example.patternforge.service.pattern.PatternGenerator;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CodeGeneratorService {
    private final CodeGeneratorFactory codeGeneratorFactory;

    private static final String CONTEXT_SUFFIX = "Context";

    public List<GeneratedFile> generateFiles(PatternContext context) throws IOException, TemplateException {
        String patternName = convertContextClassToPatternName(context.getClass());
        PatternGenerator generator = codeGeneratorFactory.getGenerator(patternName);
        generator.setContext(context);

        return generator.generateFiles();
    }

    private String convertContextClassToPatternName(Class<?> contextClass) {
        String className = contextClass.getSimpleName();
        String pattern = removeContextSuffix(className);
        return convertCamelCaseToSpaces(pattern);
    }

    private String removeContextSuffix(String className) {
        if (className.endsWith(CONTEXT_SUFFIX)) {
            return className.substring(0, className.length() - CONTEXT_SUFFIX.length());
        }
        return className;
    }

    private String convertCamelCaseToSpaces(String className) {
        return className.replaceAll("([a-z])([A-Z])", "$1 $2");
    }
}
