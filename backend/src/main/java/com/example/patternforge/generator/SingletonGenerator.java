package com.example.patternforge.generator;

import com.example.patternforge.generator.context.CodeGenerationContext;
import com.example.patternforge.generator.context.SingletonContext;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
@Component
public class SingletonGenerator implements PatternGenerator {

    private final String name = "SINGLETON";

    private final Configuration freemarkerConfig;
    private SingletonContext context;

    public void setContext(CodeGenerationContext context) {
        if (!(context instanceof SingletonContext singletonCtx)) {
            throw new IllegalArgumentException("Invalid context type.");
        }

        this.context = singletonCtx;
    }

    @Override
    public List<GeneratedFile> generateFiles() throws IOException, TemplateException {
        if (context == null) {
            throw new IllegalArgumentException("Pattern context not set.");
        }

        Map<String, Object> model = Map.of("className", context.className());

        Template template = freemarkerConfig.getTemplate("singleton/Template.ftl");
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        return List.of(new GeneratedFile(context.className() + ".java", content));
    }
}
