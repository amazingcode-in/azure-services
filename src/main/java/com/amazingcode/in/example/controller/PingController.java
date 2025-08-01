package com.amazingcode.in.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/azure-services")
public class PingController {

    private final Logger logger = LoggerFactory.getLogger(PingController.class);

    @GetMapping("/ping")
    public ResponseEntity<String> pingMessage(){
        logger.info("Request received for ping.");
        return ResponseEntity.ok("Azure services running...");
    }
}
