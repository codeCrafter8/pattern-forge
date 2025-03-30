package com.example.patternforge.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
@Component
public class SingletonGenerator implements PatternGenerator {

    private final String name = "singleton";

    private final Configuration freemarkerConfig;
    private CodeGenerationContext context;

    @Override
    public String generateCode() throws TemplateException, IOException {
        if (context == null) {
            throw new IllegalArgumentException("Pattern context not set.");
        }

        Map<String, Object> model = new HashMap<>();
        model.put("className", context.className());

        Template template = freemarkerConfig.getTemplate("singletonTemplate.ftl");

        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }
}
