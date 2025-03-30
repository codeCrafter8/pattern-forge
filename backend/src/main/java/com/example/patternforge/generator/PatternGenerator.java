package com.example.patternforge.generator;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface PatternGenerator {
    String generateCode() throws TemplateException, IOException;

    String getName();

    void setContext(CodeGenerationContext context);
}
