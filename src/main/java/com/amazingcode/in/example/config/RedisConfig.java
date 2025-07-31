package com.amazingcode.in.example.config;

import com.amazingcode.in.example.external.request.AzureOpenAiRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    RedisTemplate<String, AzureOpenAiRequest.Message> redisTemplate(RedisConnectionFactory connectionFactory) {

        RedisTemplate<String, AzureOpenAiRequest.Message> template = new RedisTemplate<>();

        RedisSerializer<Object> serializer = new GenericJackson2JsonRedisSerializer(new ObjectMapper());

        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        return template;
    }
}
