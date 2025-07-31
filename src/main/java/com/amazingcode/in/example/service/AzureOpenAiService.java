package com.amazingcode.in.example.service;

import com.amazingcode.in.example.external.client.AzureOpenAiClient;
import com.amazingcode.in.example.external.request.AzureOpenAiRequest;
import com.amazingcode.in.example.external.response.AzureOpenAiResponse;
import com.amazingcode.in.example.request.ChatRequest;
import com.amazingcode.in.example.response.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AzureOpenAiService {

    @Value("${spring.ai.azure.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.azure.openai.chat.options.deployment-name}")
    private String model;

    private final AzureOpenAiClient azureOpenAiClient;

    public AzureOpenAiService(AzureOpenAiClient azureOpenAiClient) {
        this.azureOpenAiClient = azureOpenAiClient;
    }

    public AzureOpenAiResponse chatWithAzure(AzureOpenAiRequest request) {

        return azureOpenAiClient.chat(model, apiKey, request);
    }
}

