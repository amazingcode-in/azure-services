package com.amazingcode.in.example.controller;

import com.amazingcode.in.example.request.ChatRequest;
import com.amazingcode.in.example.response.ChatResponse;
import com.amazingcode.in.example.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private final ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping("/ai")
    public ResponseEntity<ChatResponse> aiChat(@RequestBody ChatRequest request) throws Exception {
        logger.info("Request received: {}",request);
        return ResponseEntity.ok(chatService.chat(request));
    }
}
