package com.example.patternforge.controller;

import com.example.patternforge.service.PatternService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/patterns")
public class PatternController {

    private final PatternService patternService;

    @GetMapping("/{patternName}/config")
    public ResponseEntity<List<String>> getPatternConfig(@PathVariable String patternName) {
        return ResponseEntity.ok(patternService.getPatternConfig(patternName));
    }
}
