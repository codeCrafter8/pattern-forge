package com.example.patternforge.service.pattern.factorymethod;

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
public class FactoryMethodGenerator implements PatternGenerator {

    private final String name = "FACTORY METHOD";

    private final Configuration freemarkerConfig;
    private FactoryMethodContext context;

    @Override
    public void setContext(PatternContext context) {
        if (!(context instanceof FactoryMethodContext fmContext)) {
            throw new IllegalArgumentException("Invalid context type.");
        }

        this.context = fmContext;
    }

    @Override
    public List<GeneratedFile> generateFiles() throws IOException, TemplateException {
        if (context == null) {
            throw new IllegalArgumentException("%s pattern context not set.".formatted(name));
        }

        Map<String, Object> model = Map.of(
                "productInterfaceName", context.productInterfaceName(),
                "productMethodName", context.productMethodName(),
                "productClassName", context.productClassName(),
                "creatorClassName", context.creatorClassName(),
                "creatorMethodName", context.creatorMethodName(),
                "concreteCreatorClassName", context.concreteCreatorClassName()
        );

        return List.of(
                GenerationUtils.generate(freemarkerConfig,
                        context.productInterfaceName(),
                        "%s/ProductInterface.ftl".formatted(name.toLowerCase()),
                        model),
                GenerationUtils.generate(freemarkerConfig,
                        context.productClassName(),
                        "%s/ConcreteProduct.ftl".formatted(name.toLowerCase()),
                        model),
                GenerationUtils.generate(freemarkerConfig,
                        context.creatorClassName(),
                        "%s/Creator.ftl".formatted(name.toLowerCase()),
                        model),
                GenerationUtils.generate(freemarkerConfig,
                        context.concreteCreatorClassName(),
                        "%s/ConcreteCreator.ftl".formatted(name.toLowerCase()),
                        model)
        );
    }
}
