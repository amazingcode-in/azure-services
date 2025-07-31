package com.amazingcode.in.example.external.client;

import com.amazingcode.in.example.external.request.AzureOpenAiRequest;
import com.amazingcode.in.example.external.response.AzureOpenAiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "azure-openai", url = "${spring.ai.azure.openai.endpoint}")
public interface AzureOpenAiClient {

//    @PostMapping("/openai/deployments/gpt-4o/chat/completions?api-version=2024-08-01-preview")
//    AzureOpenAiResponse chat(@RequestHeader("api-key") String apiKey, @RequestBody AzureOpenAiRequest request);

    @PostMapping("/openai/deployments/{model}/chat/completions?api-version=2024-08-01-preview")
    AzureOpenAiResponse chat(
            @PathVariable("model") String model,
            @RequestHeader("api-key") String apiKey,
            @RequestBody AzureOpenAiRequest request
    );
}
