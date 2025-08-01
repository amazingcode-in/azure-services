package com.amazingcode.in.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderUtil {
    private final static Logger logger = LoggerFactory.getLogger(FileReaderUtil.class);

    public static String getSysPrompt() throws Exception {
        ClassPathResource resource = new ClassPathResource("system-prompt.txt");
        logger.info("Reading system prompts from resources.");
        return Files.readString(Path.of(resource.getURI()), StandardCharsets.UTF_8);
    }
}

