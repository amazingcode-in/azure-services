package com.amazingcode.in.example.service;

import com.amazingcode.in.example.external.request.AzureOpenAiRequest;
import com.amazingcode.in.example.external.response.AzureOpenAiResponse;
import com.amazingcode.in.example.mapper.ModelMapper;
import com.amazingcode.in.example.request.ChatRequest;
import com.amazingcode.in.example.response.ChatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final Logger logger = LoggerFactory.getLogger(ChatService.class);

    private final AzureOpenAiService azureOpenAiService;
    private final RedisDatabaseService redisDatabaseService;
    private final ModelMapper mapper;

    public ChatService(AzureOpenAiService azureOpenAiService, RedisDatabaseService redisDatabaseService, ModelMapper mapper){
        this.azureOpenAiService = azureOpenAiService;
        this.redisDatabaseService = redisDatabaseService;
        this.mapper = mapper;
    }

    public ChatResponse chat(ChatRequest request) throws Exception {
        redisDatabaseService.appendChatHistory(request);
        List<AzureOpenAiRequest.Message> chatHistory = redisDatabaseService.getChatHistory(request);
        AzureOpenAiRequest azureOpenAiRequest = mapper.createChatRequest(chatHistory);
        AzureOpenAiResponse azureOpenAiResponse = azureOpenAiService.chatWithAzure(azureOpenAiRequest);
        redisDatabaseService.appendChatHistory(request, azureOpenAiResponse);
        logger.info("Returning response: {}", azureOpenAiResponse);
        return mapper.azureOpenAiResponseToChatResponse(azureOpenAiResponse);
    }
}
