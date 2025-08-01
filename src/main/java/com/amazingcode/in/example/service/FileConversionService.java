package com.amazingcode.in.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.Base64;

@Service
public class FileConversionService {

    public byte[] convertToByteArray(MultipartFile file) throws IOException {
        return file.getBytes();
    }

//    public MultipartFile convertToMultipartFile(byte[] fileData, String fileName, String contentType) throws IOException {
//        FileItem fileItem = new DiskFileItem("file", contentType, false, fileName, fileData.length, new File(System.getProperty("java.io.tmpdir")));
//        try (OutputStream os = fileItem.getOutputStream()) {
//            os.write(fileData);
//        }
//        return new CommonsMultipartFile(fileItem);
//    }

    public String encodeFileToBase64(MultipartFile file) throws IOException {
        byte[] fileBytes = file.getBytes();
        return Base64.getEncoder().encodeToString(fileBytes);
    }

    public byte[] decodeFileFromBase64(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    public String detectImageType(byte[] bytes) {
        if (bytes.length >= 2) {
            if (bytes[0] == (byte) 0xFF && bytes[1] == (byte) 0xD8) {
                return "image/jpeg";
            } else if (bytes[0] == (byte) 0x89 && bytes[1] == (byte) 0x50) {
                return "image/png";
            } else if (bytes[0] == (byte) 0x47 && bytes[1] == (byte) 0x49) {
                return "image/gif";
            }
        }
        return "application/octet-stream";
    }
}
