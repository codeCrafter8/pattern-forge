package com.example.patternforge.controller;

import com.example.patternforge.generator.GeneratedFile;
import com.example.patternforge.generator.context.CodeGenerationContext;
import com.example.patternforge.service.CodeGeneratorService;
import freemarker.template.TemplateException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/code-generator")
public class CodeGeneratorController {

    private final CodeGeneratorService codeGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<List<GeneratedFile>> generateCode(
            @RequestBody @Valid CodeGenerationContext context) throws TemplateException, IOException {
        List<GeneratedFile> result = codeGeneratorService.generateFiles(context);

        return ResponseEntity.ok(result);
    }
}
