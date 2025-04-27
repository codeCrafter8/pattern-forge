package com.example.patternforge.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class TemplateReader {

    private static final String TEMPLATES_DIR = "templates";
    private static final String TEMPLATE_EXTENSION = ".ftl";

    public File[] getTemplateFiles(String patternName) throws IOException {
        String templatePath = TEMPLATES_DIR + "/" + patternName;
        ClassPathResource patternDir = new ClassPathResource(templatePath);

        if (!patternDir.exists()) {
            log.error("Pattern directory {} not found", templatePath);
            throw new IOException(String.format("Template directory not found for pattern: %s", patternName));
        }

        File[] files = patternDir.getFile().listFiles(this::isTemplateFile);

        if (files == null || files.length == 0) {
            log.error("No {} templates found for pattern: {}", TEMPLATE_EXTENSION, patternName);
            throw new IOException(String.format("No templates found for pattern: %s", patternName));
        }

        return files;
    }

    private boolean isTemplateFile(File dir, String name) {
        return name.endsWith(TEMPLATE_EXTENSION);
    }

}
