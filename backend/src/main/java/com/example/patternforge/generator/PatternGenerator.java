package com.example.patternforge.generator;

import com.example.patternforge.generator.context.CodeGenerationContext;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;

public interface PatternGenerator {
    String getName();

    void setContext(CodeGenerationContext context);

    List<GeneratedFile> generateFiles() throws TemplateException, IOException;
}
