package com.example.patternforge.util;

import com.example.patternforge.dto.GeneratedFile;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenerationUtils {

    public static GeneratedFile generate(
            Configuration freemarkerConfig,
            String fileName,
            String templatePath,
            Map<String, Object> model,
            String extension) throws IOException, TemplateException {

        Template template = freemarkerConfig.getTemplate(templatePath);
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        return new GeneratedFile(fileName + extension, content);
    }
}
