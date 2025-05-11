package com.example.patternforge.controller;

import com.example.patternforge.service.PatternConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/patterns")
public class PatternController {

    private final PatternConfigService patternConfigService;

    @GetMapping("/{patternName}/config")
    public ResponseEntity<Set<String>> getPatternConfig(@PathVariable String patternName) {
        return ResponseEntity.ok(patternConfigService.getPatternConfig(patternName));
    }
}
