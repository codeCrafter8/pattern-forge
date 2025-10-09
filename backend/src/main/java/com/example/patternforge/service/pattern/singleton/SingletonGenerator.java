package com.example.patternforge.service.pattern.singleton;

import com.example.patternforge.dto.GeneratedFile;
import com.example.patternforge.service.pattern.PatternContext;
import com.example.patternforge.service.pattern.PatternGenerator;
import com.example.patternforge.service.pattern.ProgrammingLanguage;
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

    private static final String JAVA_FILENAME = "%s.java";
    private static final String CPP_FILENAME = "%s.cpp";

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

        String language = context.language().toLowerCase();
        String templatePath = "%s/%s/Singleton.ftl".formatted(name.toLowerCase(), language);
        Template template = freemarkerConfig.getTemplate(templatePath);

        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        String fileName = (language.equals(ProgrammingLanguage.CPP.getValue()) ? CPP_FILENAME : JAVA_FILENAME)
                .formatted(context.className());

        return List.of(new GeneratedFile(fileName, content));
    }

}
