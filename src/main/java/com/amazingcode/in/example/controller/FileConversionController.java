package com.amazingcode.in.example.controller;

import com.amazingcode.in.example.response.SuccessResponse;
import com.amazingcode.in.example.service.FileConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file-conversion")
public class FileConversionController {

    private final FileConversionService fileConversionService;

    public FileConversionController(FileConversionService fileConversionService){
        this.fileConversionService = fileConversionService;
    }

    @PostMapping("/encode-file")
    public ResponseEntity<SuccessResponse> encodeFileToBase64(@RequestParam("file") MultipartFile file) throws IOException {
        String encodedFile = fileConversionService.encodeFileToBase64(file);
        SuccessResponse response = SuccessResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .message("File encoded successfully.")
                .data(encodedFile)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/decode-file")
    public ResponseEntity<byte[]> decodeFileFromBase64(@RequestParam("base64") String base64) {
        byte[] decodedBytes = fileConversionService.decodeFileFromBase64(base64);
        String contentType = fileConversionService.detectImageType(decodedBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        return ResponseEntity.ok().headers(headers).body(decodedBytes);
    }

}
