package com.studygram.config;

import com.studygram.mapper.NotificationMapper;
import com.studygram.repository.EmitterRepository;
import com.studygram.repository.EmitterRepositoryImpl;
import com.studygram.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SseConfig {

    @Bean
    public EmitterRepository emitterRepository() {
        return new EmitterRepositoryImpl();
    }
}
