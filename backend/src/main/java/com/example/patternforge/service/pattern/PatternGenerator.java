package com.example.patternforge.service.pattern;

import com.example.patternforge.dto.GeneratedFile;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;

public interface PatternGenerator {
    String getName();

    void setContext(PatternContext context);

    List<GeneratedFile> generateFiles() throws TemplateException, IOException;
}
