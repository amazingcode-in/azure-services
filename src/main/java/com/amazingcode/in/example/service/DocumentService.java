package com.amazingcode.in.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class DocumentService {

    private final FileConversionService fileConversionService;
    private final AzureDocumentIntellijenceService azureDocumentIntellijenceService;

    public DocumentService(FileConversionService fileConversionService, AzureDocumentIntellijenceService azureDocumentIntellijenceService){
        this.fileConversionService = fileConversionService;
        this.azureDocumentIntellijenceService = azureDocumentIntellijenceService;
    }

    public Map<String, String> scanDocument(MultipartFile file) throws IOException {
        String encodedFile = fileConversionService.encodeFileToBase64(file);
        String fileData = encodedFile;
        if(encodedFile.contains(",")){
            fileData = encodedFile.split(",")[1];
        }
        byte[] fileBytes = fileConversionService.decodeFileFromBase64(fileData);

        return azureDocumentIntellijenceService.extractDataFromPrebuildIdDocument(fileBytes);
    }
}
