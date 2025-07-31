package com.amazingcode.in.example.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AzureOpenAiResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
    @JsonProperty("system_fingerprint")
    private String systemFingerprint;

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {

        @JsonProperty("index")
        private int index;

        @JsonProperty("finish_reason")
        private String finishReason;

        @JsonProperty("logprobs")
        private LogProbs logprobs;

        @JsonProperty("message")
        private Message message;

        @JsonProperty("content_filter_results")
        private ContentFilterResults contentFilterResults;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {

        @JsonProperty("role")
        private String role;

        @JsonProperty("content")
        private String content;

        @JsonProperty("refusal")
        private String refusal;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LogProbs {

        @JsonProperty("placeholder")
        private String placeholder;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContentFilterResults {

        private FilterCategory hate;

        @JsonProperty("self_harm")
        private FilterCategory selfHarm;

        private FilterCategory sexual;

        private FilterCategory violence;

        @JsonProperty("protected_material_code")
        private FilterCategory protectedMaterialCode;

        @JsonProperty("protected_material_text")
        private FilterCategory protectedMaterialText;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FilterCategory {

        @JsonProperty("filtered")
        private boolean filtered;

        @JsonProperty("severity")
        private String severity;

        @JsonProperty("detected")
        private boolean detected;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Usage {

        @JsonProperty("completion_tokens")
        private int completionTokens;

        @JsonProperty("completion_tokens_details")
        private CompletionTokensDetails completionTokensDetails;

        @JsonProperty("prompt_tokens")
        private int promptTokens;

        @JsonProperty("prompt_tokens_details")
        private PromptTokensDetails promptTokensDetails;

        @JsonProperty("total_tokens")
        private int totalTokens;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompletionTokensDetails {

        @JsonProperty("accepted_prediction_tokens")
        private int acceptedPredictionTokens;

        @JsonProperty("audio_tokens")
        private int audioTokens;

        @JsonProperty("reasoning_tokens")
        private int reasoningTokens;

        @JsonProperty("rejected_prediction_tokens")
        private int rejectedPredictionTokens;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PromptTokensDetails {

        @JsonProperty("audio_tokens")
        private int audioTokens;

        @JsonProperty("cached_tokens")
        private int cachedTokens;
    }
}
