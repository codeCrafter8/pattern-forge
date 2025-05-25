package com.example.patternforge.service;

import com.example.patternforge.dto.GeneratedFile;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ZipGeneratorService {

    public byte[] generateZip(List<GeneratedFile> generatedFiles) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
            for (GeneratedFile file : generatedFiles) {
                addFileToZip(zipOutputStream, file);
            }
        }

        return byteArrayOutputStream.toByteArray();
    }

    private void addFileToZip(ZipOutputStream zipOutputStream, GeneratedFile file) throws IOException {
        ZipEntry zipEntry = new ZipEntry(file.fileName());
        zipOutputStream.putNextEntry(zipEntry);

        byte[] contentBytes = file.content().getBytes();
        zipOutputStream.write(contentBytes, 0, contentBytes.length);

        zipOutputStream.closeEntry();
    }
}
