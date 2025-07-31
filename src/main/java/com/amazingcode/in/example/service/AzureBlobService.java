package com.amazingcode.in.example.service;

import com.amazingcode.in.example.exception.FileUploadException;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AzureBlobService {

    @Value("${azure.storage.blob.container-name}")
    private String containerName;

    private final BlobServiceClient blobServiceClient;

    public AzureBlobService(BlobServiceClient blobServiceClient) {
        this.blobServiceClient = blobServiceClient;
    }

    public Map<String, String> uploadMultipleImages(MultipartFile[] files, String destinationContainerName) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(destinationContainerName);
        Map<String, String> map = new LinkedHashMap<>();

        for (MultipartFile file : files) {
            try {
                BlobClient blobClient = containerClient.getBlobClient(Objects.requireNonNull(file.getOriginalFilename()));
                blobClient.upload(file.getInputStream(), file.getSize(), true);
                map.put(file.getOriginalFilename(), blobClient.getBlobUrl());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return map;
    }

    public String uploadFileToBlob(String fileName, byte[] file){
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = containerClient.getBlobClient(fileName);

        blobClient.upload(new ByteArrayInputStream(file), file.length, true);

        if(!blobClient.exists()){
            throw new FileUploadException("Failed to upload file: " + fileName);
        }
        return blobClient.getBlobUrl();
    }

    public byte[] downloadFileFromBlob(String url){
        return new byte[0];
    }
}
