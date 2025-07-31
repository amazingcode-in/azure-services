package com.amazingcode.in.example.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    private Integer statusCode;
    private HttpStatus status;
    private String statusMsg;
    private DataWrapper data;

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataWrapper {
        private List<Message> messages;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String msgType;
        private String promptType;
        private String msgTxt;
        private String msgImg;
        private List<Option> options;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Option {
        private String optStatus;
        private String optType;
        private String optTxt;
        private String optImg;
        private String optNote;
        private String optExtra;
        private String workflow;
        private String workflowStatus;
        private Point point;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Point{
        private int planner;
        private int adventure;
        private int contributor;
        private int goalSetter;
    }
}
