package com.example.patternforge.service.pattern.adapter;

import com.example.patternforge.dto.GeneratedFile;
import com.example.patternforge.service.pattern.PatternContext;
import com.example.patternforge.service.pattern.PatternGenerator;
import com.example.patternforge.util.GenerationUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
@Component
public class AdapterGenerator implements PatternGenerator {

    private final String name = "ADAPTER";

    private final Configuration freemarkerConfig;
    private AdapterContext context;

    public void setContext(PatternContext context) {
        if (!(context instanceof AdapterContext adapterCtx)) {
            throw new IllegalArgumentException("Invalid context type.");
        }

        this.context = adapterCtx;
    }

    @Override
    public List<GeneratedFile> generateFiles() throws IOException, TemplateException {
        if (context == null) {
            throw new IllegalArgumentException("%s pattern context not set.".formatted(name));
        }

        Map<String, Object> model = Map.of(
                "clientClassName", context.clientClassName(),
                "adapterClassName", context.adapterClassName(),
                "targetInterfaceName", context.targetInterfaceName(),
                "adapteeClassName", context.adapteeClassName(),
                "adapteeMethodName", context.adapteeMethodName()
        );

        return List.of(
                GenerationUtils.generate(freemarkerConfig,
                        context.targetInterfaceName(),
                        "%s/Target.ftl".formatted(name.toLowerCase()),
                        model),
                GenerationUtils.generate(freemarkerConfig,
                        context.adapteeClassName(),
                        "%s/Adaptee.ftl".formatted(name.toLowerCase()),
                        model),
                GenerationUtils.generate(freemarkerConfig,
                        context.adapterClassName(),
                        "%s/Adapter.ftl".formatted(name.toLowerCase()),
                        model),
                GenerationUtils.generate(freemarkerConfig,
                        context.clientClassName(),
                        "%s/Client.ftl".formatted(name.toLowerCase()),
                        model)
        );
    }
}
