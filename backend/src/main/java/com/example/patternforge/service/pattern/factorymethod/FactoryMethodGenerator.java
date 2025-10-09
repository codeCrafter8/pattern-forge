package com.example.patternforge.service.pattern.factorymethod;

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
public class FactoryMethodGenerator implements PatternGenerator {

    private final String name = "FACTORY METHOD";

    private static final String JAVA_EXTENSION = ".java";
    private static final String CPP_EXTENSION = ".cpp";

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

        Map<String, Object> sharedModel = Map.of(
                "productInterfaceName", context.productInterfaceName(),
                "productMethodName", context.productMethodName(),
                "creatorClassName", context.creatorClassName(),
                "creatorMethodName", context.creatorMethodName()
        );

        String language = context.language().toLowerCase();
        String extension = language.equals(ProgrammingLanguage.CPP.getValue()) ?
                CPP_EXTENSION : JAVA_EXTENSION;
        String baseTemplatePath = "%s/%s/".formatted(name.toLowerCase(), language);

        List<GeneratedFile> files = new ArrayList<>();

        files.add(GenerationUtils.generate(
                freemarkerConfig,
                context.productInterfaceName(),
                baseTemplatePath + "ProductInterface.ftl",
                sharedModel,
                extension
        ));

        files.add(GenerationUtils.generate(
                freemarkerConfig,
                context.creatorClassName(),
                baseTemplatePath + "Creator.ftl",
                sharedModel,
                extension
        ));

        for (FactoryMethodContext.ProductVariant variant : context.productVariants()) {
            Map<String, Object> variantModel = new HashMap<>(sharedModel);
            variantModel.put("productClassName", variant.productClassName());
            variantModel.put("concreteCreatorClassName", variant.concreteCreatorClassName());

            files.add(GenerationUtils.generate(
                    freemarkerConfig,
                    variant.productClassName(),
                    baseTemplatePath + "ConcreteProduct.ftl",
                    variantModel,
                    extension
            ));

            files.add(GenerationUtils.generate(
                    freemarkerConfig,
                    variant.concreteCreatorClassName(),
                    baseTemplatePath + "ConcreteCreator.ftl",
                    variantModel,
                    extension
            ));
        }

        return files;
    }
}
