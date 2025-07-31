package com.amazingcode.in.example.service;

import com.amazingcode.in.example.external.client.AzureBlobStorageClient;
import com.amazingcode.in.example.external.request.AzureOpenAiRequest;
import com.amazingcode.in.example.external.response.AzureOpenAiResponse;
import com.amazingcode.in.example.request.ChatRequest;
import com.amazingcode.in.example.util.FileReaderUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisDatabaseService {

    private final RedisTemplate<String, AzureOpenAiRequest.Message> redisTemplate;
    private final AzureBlobStorageClient azureBlobStorageClient;

    public RedisDatabaseService(RedisTemplate<String, AzureOpenAiRequest.Message> redisTemplate, AzureBlobStorageClient azureBlobStorageClient){
        this.redisTemplate = redisTemplate;
        this.azureBlobStorageClient = azureBlobStorageClient;
    }

    private static final String CHAT_PREFIX = "CUSTOMER:";

    public  void appendChatHistory(ChatRequest request) throws Exception {
        String key = CHAT_PREFIX + request.getSessionId();

        Boolean exists = redisTemplate.hasKey(key);
        if (Boolean.FALSE.equals(exists)){
            AzureOpenAiRequest.Message sysMessage = new AzureOpenAiRequest.Message();
            AzureOpenAiRequest.Content sysContent = new AzureOpenAiRequest.Content();
            sysContent.setType("text");
//            String sysPrompt = azureBlobStorageClient.getBlobContent();
            String sysPrompt = FileReaderUtil.getSysPrompt();
            sysContent.setText(sysPrompt);
            sysMessage.setRole("system");
            sysMessage.setContent(List.of(sysContent));
            redisTemplate.opsForList().rightPush(key, sysMessage);
        }
        AzureOpenAiRequest.Message message = new AzureOpenAiRequest.Message();
        AzureOpenAiRequest.Content content = new AzureOpenAiRequest.Content();
        content.setType("text");
        content.setText(request.getCustomerQuery());
        message.setRole("user");
        message.setContent(List.of(content));
        redisTemplate.opsForList().rightPush(key, message);
    }

    public void appendChatHistory(ChatRequest request, AzureOpenAiResponse response){
        String key = CHAT_PREFIX + request.getSessionId();

        AzureOpenAiRequest.Message message = new AzureOpenAiRequest.Message();
        AzureOpenAiRequest.Content content = new AzureOpenAiRequest.Content();
        content.setType("text");
        content.setText(response.getChoices().getFirst().getMessage().getContent());
        message.setRole(response.getChoices().getFirst().getMessage().getRole());
        message.setContent(List.of(content));
        redisTemplate.opsForList().rightPush(key, message);
    }

    public List<AzureOpenAiRequest.Message> getChatHistory(ChatRequest request){
        String key = CHAT_PREFIX + request.getSessionId();

        return redisTemplate.opsForList().range(key, 0, -1);
    }
}
