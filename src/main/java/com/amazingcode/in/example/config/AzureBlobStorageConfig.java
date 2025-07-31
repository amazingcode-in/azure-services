package com.amazingcode.in.example.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobStorageConfig {

    @Value("${azure.storage.blob.connection-string}")
    private String connectionString;

    @Bean
    BlobServiceClient getBlobServiceClient(){
        return new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }
}
