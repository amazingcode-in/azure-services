package com.amazingcode.in.example.external.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AzureOpenAiRequest {

    @JsonProperty("messages")
    private List<Message> messages;

    @JsonProperty("temperature")
    private double temperature = 0.7;

    @JsonProperty("top_p")
    private double topP = 0.95;

    @JsonProperty("frequency_penalty")
    private int frequencyPenalty = 0;

    @JsonProperty("presence_penalty")
    private int presencePenalty = 0;

    @JsonProperty("max_tokens")
    private int maxTokens = 800;

    @JsonProperty("stop")
    private String stop = null;

    @JsonProperty("stream")
    private boolean stream = false;

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {

        @JsonProperty("role")
        private String role;

        @JsonProperty("content")
        private List<Content> content;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Content {

        @JsonProperty("type")
        private String type;

        @JsonProperty("text")
        private String text;
    }
}
