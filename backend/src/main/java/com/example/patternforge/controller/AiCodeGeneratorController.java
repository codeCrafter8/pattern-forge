package com.example.patternforge.controller;

import com.example.patternforge.dto.AiPatternRequest;
import com.example.patternforge.dto.AiPatternResponse;
import com.example.patternforge.service.AiGeneratorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/code-generator/ai")
public class AiCodeGeneratorController {

    private final AiGeneratorService aiGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<AiPatternResponse> generateAiCode(
            @RequestBody @Valid AiPatternRequest request) {

        AiPatternResponse response = aiGeneratorService.generateCode(request);

        return ResponseEntity.ok(response);
    }
}
