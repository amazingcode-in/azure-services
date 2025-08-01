package com.amazingcode.in.example.config;

import com.azure.ai.documentintelligence.DocumentIntelligenceClient;
import com.azure.ai.documentintelligence.DocumentIntelligenceClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureServiceConfig {

    @Value("${azure.di.key}")
    private String key;

    @Value("${azure.di.endpoint}")
    private String endpoint;

    @Bean
    public DocumentIntelligenceClient documentIntelligenceClient() {
        return new DocumentIntelligenceClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();
    }
}
