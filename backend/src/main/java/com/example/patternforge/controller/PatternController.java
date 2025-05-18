package com.example.patternforge.controller;

import com.example.patternforge.dto.VariableExtractionResult;
import com.example.patternforge.service.PatternConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/patterns")
public class PatternController {

    private final PatternConfigService patternConfigService;

    @GetMapping("/{patternName}/config")
    public ResponseEntity<VariableExtractionResult> getPatternConfig(@PathVariable String patternName) {
        return ResponseEntity.ok(patternConfigService.getPatternConfig(patternName));
    }
}
