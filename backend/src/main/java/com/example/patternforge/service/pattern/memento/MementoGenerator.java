package com.example.patternforge.service.pattern.memento;

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
public class MementoGenerator implements PatternGenerator {

    private final String name = "MEMENTO";

    private static final String JAVA_EXTENSION = ".java";
    private static final String CPP_EXTENSION = ".cpp";

    private final Configuration freemarkerConfig;
    private MementoContext context;

    @Override
    public void setContext(PatternContext context) {
        if (!(context instanceof MementoContext mContext)) {
            throw new IllegalArgumentException("Invalid context type.");
        }
        this.context = mContext;
    }

    @Override
    public List<GeneratedFile> generateFiles() throws IOException, TemplateException {
        if (context == null) {
            throw new IllegalArgumentException("%s pattern context not set.".formatted(name));
        }

        String language = context.language().toLowerCase();
        String extension = language.equals(ProgrammingLanguage.CPP.getValue()) ? CPP_EXTENSION : JAVA_EXTENSION;
        String baseTemplatePath = "%s/%s/".formatted(name.toLowerCase(), language);

        Map<String, Object> model = new HashMap<>();
        model.put("originatorClassName", context.originatorClassName());
        model.put("mementoClassName", context.mementoClassName());
        model.put("caretakerClassName", context.caretakerClassName());
        model.put("stateFieldName", context.stateFieldName());
        model.put("undoEnabled", context.undoEnabled());

        List<GeneratedFile> files = new ArrayList<>();

        files.add(GenerationUtils.generate(
                freemarkerConfig,
                context.originatorClassName(),
                baseTemplatePath + "Originator.ftl",
                model,
                extension
        ));

        files.add(GenerationUtils.generate(
                freemarkerConfig,
                context.mementoClassName(),
                baseTemplatePath + "Memento.ftl",
                model,
                extension
        ));

        files.add(GenerationUtils.generate(
                freemarkerConfig,
                context.caretakerClassName(),
                baseTemplatePath + "Caretaker.ftl",
                model,
                extension
        ));

        return files;
    }
}
