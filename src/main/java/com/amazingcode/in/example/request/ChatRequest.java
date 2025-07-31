package com.amazingcode.in.example.request;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String sessionId;
    private String customerQuery;
}
