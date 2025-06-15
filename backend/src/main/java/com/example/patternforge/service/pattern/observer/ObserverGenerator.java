package com.example.patternforge.service.pattern.observer;

import com.example.patternforge.dto.GeneratedFile;
import com.example.patternforge.service.pattern.PatternContext;
import com.example.patternforge.service.pattern.PatternGenerator;
import com.example.patternforge.service.pattern.ProgrammingLanguage;
import com.example.patternforge.util.GenerationUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
@Component
public class ObserverGenerator implements PatternGenerator {

    private final String name = "OBSERVER";

    private static final String JAVA_EXTENSION = ".java";
    private static final String CPP_EXTENSION = ".cpp";

    private final Configuration freemarkerConfig;
    private ObserverContext context;

    @Override
    public void setContext(PatternContext context) {
        if (!(context instanceof ObserverContext obsContext)) {
            throw new IllegalArgumentException("Invalid context type.");
        }
        this.context = obsContext;
    }

    @Override
    public List<GeneratedFile> generateFiles() throws IOException, TemplateException {
        if (context == null) {
            throw new IllegalArgumentException("%s pattern context not set.".formatted(name));
        }

        Map<String, Object> model = Map.of(
                "subjectInterfaceName", context.subjectInterfaceName(),
                "observerInterfaceName", context.observerInterfaceName(),
                "updateMethodName", context.updateMethodName(),
                "concreteSubjectClassName", context.concreteSubjectClassName()
        );

        String language = context.language().toLowerCase();
        String extension = language.equals(ProgrammingLanguage.CPP.getValue()) ?
                CPP_EXTENSION : JAVA_EXTENSION;
        String baseTemplatePath = "%s/%s/".formatted(name.toLowerCase(), language);

        List<GeneratedFile> files = new ArrayList<>();

        files.add(GenerationUtils.generate(freemarkerConfig,
                context.subjectInterfaceName(),
                baseTemplatePath + "SubjectInterface.ftl",
                model,
                extension));

        files.add(GenerationUtils.generate(freemarkerConfig,
                context.observerInterfaceName(),
                baseTemplatePath + "ObserverInterface.ftl",
                model,
                extension));

        files.add(GenerationUtils.generate(freemarkerConfig,
                context.concreteSubjectClassName(),
                baseTemplatePath + "ConcreteSubject.ftl",
                model,
                extension));

        for (String concreteObserver : context.concreteObserverClassName()) {
            Map<String, Object> observerModel = new HashMap<>(model);
            observerModel.put("concreteObserverClassName", concreteObserver);

            files.add(GenerationUtils.generate(freemarkerConfig,
                    concreteObserver,
                    baseTemplatePath + "ConcreteObserver.ftl",
                    observerModel,
                    extension));
        }

        return files;
    }
}
