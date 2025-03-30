package com.example.patternforge.controller;

import com.example.patternforge.service.CodeGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/code-generator")
public class CodeGeneratorController {
    private final CodeGeneratorService codeGeneratorService;

    @PostMapping("/generate/{pattern}")
    public ResponseEntity<String> generateCode(@PathVariable String pattern) {
        String result = codeGeneratorService.generateCode(pattern);
        return ResponseEntity.ok(result);
    }
}
