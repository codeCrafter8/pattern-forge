package com.example.patternforge.controller;

import com.example.patternforge.generator.CodeGenerationContext;
import com.example.patternforge.service.CodeGeneratorService;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/code-generator")
public class CodeGeneratorController {
    private final CodeGeneratorService codeGeneratorService;

    @PostMapping("/generate/{pattern}")
    public ResponseEntity<String> generateCode(
            @PathVariable String pattern,
            @RequestBody CodeGenerationContext context) throws TemplateException, IOException {
        String result = codeGeneratorService.generateCode(pattern, context);
        return ResponseEntity.ok(result);
    }
}
