package com.amazingcode.in.example.controller;
import com.amazingcode.in.example.request.DownloadFileRequest;
import com.amazingcode.in.example.request.UploadMultipleFilesRequest;
import com.amazingcode.in.example.response.DownloadFileResponse;
import com.amazingcode.in.example.response.SuccessResponse;
import com.amazingcode.in.example.response.UploadFileResponse;
import com.amazingcode.in.example.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/blob")
public class StorageController {

    private final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload-file")
    public ResponseEntity<SuccessResponse<Object>> uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("Request received to upload file:");
        UploadFileResponse uploadFileResponse = new UploadFileResponse();
        SuccessResponse<Object> response = SuccessResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .message("File uploaded successfully.")
                .data(uploadFileResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/upload-multiple-files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SuccessResponse> uploadMultipleImages(@RequestParam("files") MultipartFile[] files, @RequestBody UploadMultipleFilesRequest request) {
        Map<String, String> data = storageService.uploadMultipleImages(files, request);
        SuccessResponse response = SuccessResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .message("Files uploaded successfully.")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/download-file")
    public ResponseEntity<SuccessResponse> downloadFile(@RequestBody DownloadFileRequest request){
        logger.info("Request received for download file: {}", request);
        DownloadFileResponse downloadFileResponse = new DownloadFileResponse();
        SuccessResponse response = SuccessResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .message("File download successfully.")
                .data(downloadFileResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
