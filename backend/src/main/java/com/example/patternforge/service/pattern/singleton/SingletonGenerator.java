package com.example.patternforge.service.pattern.singleton;

import com.example.patternforge.dto.GeneratedFile;
import com.example.patternforge.service.pattern.PatternContext;
import com.example.patternforge.service.pattern.PatternGenerator;
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

    private static final String JAVA_FILENAME = "%s.java";

    private final String name = "SINGLETON";

    private final Configuration freemarkerConfig;
    private SingletonContext context;

    public void setContext(PatternContext context) {
        if (!(context instanceof SingletonContext singletonCtx)) {
            throw new IllegalArgumentException("Invalid context type.");
        }

        this.context = singletonCtx;
    }

    @Override
    public List<GeneratedFile> generateFiles() throws IOException, TemplateException {
        if (context == null) {
            throw new IllegalArgumentException("%s pattern context not set.".formatted(name));
        }

        Map<String, Object> model = Map.of("className", context.className());

        Template template = freemarkerConfig.getTemplate("%s/Singleton.ftl".formatted(name.toLowerCase()));
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        return List.of(new GeneratedFile(JAVA_FILENAME.formatted(context.className()), content));
    }
}
