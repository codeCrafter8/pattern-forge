package com.example.patternforge.controller;

import com.example.patternforge.dto.GeneratedFile;
import com.example.patternforge.service.ZipGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/zip-generator")
public class ZipGeneratorController {

    private final ZipGeneratorService zipGeneratorService;

    @PostMapping("/download")
    public ResponseEntity<byte[]> generateZip(
            @RequestBody List<GeneratedFile> generatedFiles) throws IOException {
        byte[] zipData = zipGeneratorService.generateZip(generatedFiles);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=pattern.zip");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/zip");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(zipData.length));

        return new ResponseEntity<>(zipData, headers, HttpStatus.OK);
    }
}
