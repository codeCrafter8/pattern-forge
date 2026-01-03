package com.example.patternforge.service;

import com.example.patternforge.dto.AiPatternRequest;
import com.example.patternforge.dto.GeneratedFile;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AiGeneratorService {

    private final ChatClient chatClient;

    @Value("classpath:prompts/ai_pattern_prompt.txt")
    private Resource promptResource;

    public List<GeneratedFile> generateCode(AiPatternRequest request) {
        String prompt = loadPrompt(request);

        String response = chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();

        return parseGeneratedFiles(response);
    }

    private String loadPrompt(AiPatternRequest request) {
        try (InputStream is = promptResource.getInputStream()) {

            String template = new String(is.readAllBytes());
            return template.formatted(request.problemDescription());

        } catch (IOException e) {
            throw new IllegalStateException("Cannot load AI prompt", e);
        }
    }

    private List<GeneratedFile> parseGeneratedFiles(String response) {
        List<GeneratedFile> files = new ArrayList<>();
        String[] parts = response.split("FILE:");

        for (String part : parts) {
            String trimmed = part.trim();
            if (trimmed.isEmpty()) continue;

            int newline = trimmed.indexOf("\n");

            String fileName = newline != -1 ? trimmed.substring(0, newline).trim() : trimmed;
            String content = newline != -1 ? trimmed.substring(newline + 1).trim() : "";

            files.add(new GeneratedFile(fileName, content));
        }

        return files;
    }
}
