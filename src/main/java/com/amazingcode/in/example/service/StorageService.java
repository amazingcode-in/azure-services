package com.amazingcode.in.example.service;

import com.amazingcode.in.example.request.UploadMultipleFilesRequest;
import com.amazingcode.in.example.response.UploadFileResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class StorageService {
    private final AzureBlobService azureBlobService;
    private final FileConversionService fileConversionService;

    public StorageService(AzureBlobService azureBlobService, FileConversionService fileConversionService){
        this.azureBlobService = azureBlobService;
        this.fileConversionService = fileConversionService;
    }

    public UploadFileResponse uploadFile(MultipartFile file) throws IOException {
        byte[] byteFile = fileConversionService.convertToByteArray(file);
        String fileUrl = azureBlobService.uploadFileToBlob(file.getOriginalFilename(), byteFile);
        return new UploadFileResponse(file.getOriginalFilename(), fileUrl);
    }

    public Map<String, String> uploadMultipleImages(MultipartFile[] files, UploadMultipleFilesRequest request){
        return azureBlobService.uploadMultipleImages(files, request.getContainerName());
    }
}
