package com.amazingcode.in.example.mapper;

import com.amazingcode.in.example.external.request.AzureOpenAiRequest;
import com.amazingcode.in.example.external.response.AzureOpenAiResponse;
import com.amazingcode.in.example.request.ChatRequest;
import com.amazingcode.in.example.response.ChatResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ModelMapper {

    public AzureOpenAiRequest createChatRequest(List<AzureOpenAiRequest.Message> messages){
        AzureOpenAiRequest azureOpenAiRequest = new AzureOpenAiRequest();
//        List<AzureOpenAiRequest.Message> messages = new LinkedList<>();
//        AzureOpenAiRequest.Message message = new AzureOpenAiRequest.Message();
//        List<AzureOpenAiRequest.Content> contents = new LinkedList<>();
//        AzureOpenAiRequest.Content content = new AzureOpenAiRequest.Content();
//        content.setType("text");
//        content.setText(request.getCustomerQuery());
//        contents.add(content);
//        message.setRole("user");
//        message.setContent(contents);
//        messages.add(message);
        azureOpenAiRequest.setMessages(messages);
        return azureOpenAiRequest;
    }

    public ChatResponse azureOpenAiResponseToChatResponse(AzureOpenAiResponse response){
        ObjectMapper objectMapper = new ObjectMapper();
        ChatResponse chatResponse;
        try {
            chatResponse = objectMapper
                    .readValue(response.getChoices()
                            .get(0)
//                            .getFirst()
                            .getMessage()
                            .getContent(), ChatResponse.class);
            return chatResponse;
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ChatResponse();
        }
    }
}
