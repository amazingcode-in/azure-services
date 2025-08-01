package com.amazingcode.in.example.exception;

import lombok.Getter;

@Getter
public class FileUploadException extends RuntimeException {

    private final String message;

    public FileUploadException(String message) {
        super(message);
        this.message = message;
    }
}

