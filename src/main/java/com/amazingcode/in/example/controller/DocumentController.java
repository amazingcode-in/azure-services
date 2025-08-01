package com.amazingcode.in.example.controller;

import com.amazingcode.in.example.response.SuccessResponse;
import com.amazingcode.in.example.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }

    @PostMapping("/scan/extract-data")
    public ResponseEntity<SuccessResponse> scanDocument(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, String> data = documentService.scanDocument(file);
        SuccessResponse response = SuccessResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .message("File encoded successfully.")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }
}
