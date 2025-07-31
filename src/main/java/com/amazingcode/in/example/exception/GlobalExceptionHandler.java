package com.amazingcode.in.example.exception;

import com.amazingcode.in.example.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ErrorResponse> handleFileUploadException(FileUploadException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Default to 500

        if (ex instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST; // 400 for bad input
        } else if (ex instanceof IllegalStateException) {
            status = HttpStatus.CONFLICT; // 409 for logical issues
        }

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status)
                .code(status.value())
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(status).body(errorResponse);
    }
}
