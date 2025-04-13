package com.example.patternforge.generator;

import com.example.patternforge.generator.context.AdapterContext;
import com.example.patternforge.generator.context.CodeGenerationContext;
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
public class AdapterGenerator implements PatternGenerator {

    private final String name = "ADAPTER";

    private final Configuration freemarkerConfig;
    private AdapterContext context;

    public void setContext(CodeGenerationContext context) {
        if (!(context instanceof AdapterContext adapterCtx)) {
            throw new IllegalArgumentException("Invalid context type.");
        }

        this.context = adapterCtx;
    }

    @Override
    public List<GeneratedFile> generateFiles() throws IOException, TemplateException {
        if (context == null) {
            throw new IllegalArgumentException("Pattern context not set.");
        }

        Map<String, Object> model = Map.of(
                "clientClassName", context.clientClassName(),
                "adapterClassName", context.adapterClassName(),
                "targetInterfaceName", context.targetInterfaceName(),
                "adapteeClassName", context.adapteeClassName(),
                "adapteeMethodName", context.adapteeMethodName()
        );

        return List.of(
                generate("Client.java", "adapter/Client.ftl", model),
                generate("Target.java", "adapter/Target.ftl", model),
                generate("Adapter.java", "adapter/Adapter.ftl", model),
                generate("Adaptee.java", "adapter/Adaptee.ftl", model)
        );
    }

    private GeneratedFile generate(String fileName, String templatePath, Map<String, Object> model)
            throws IOException, TemplateException {

        Template template = freemarkerConfig.getTemplate(templatePath);
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        return new GeneratedFile(fileName, content);
    }
}
