package com.amazingcode.in.example.response;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DownloadFileResponse {
    private byte[] file;
}
