package com.amazingcode.in.example.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "azureBlobStorageClient", url = "${system.message.url}")
public interface AzureBlobStorageClient {
    @GetMapping
    String getBlobContent();
}
