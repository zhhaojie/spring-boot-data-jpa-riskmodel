package com.example.riskmodel.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AppConfig {

    @Bean(name = "LoadModelThreadPool")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setQueueCapacity(100);
        executor.setMaxPoolSize(30); //队列都挤满后，增加30-20 = 10人力
        executor.setThreadNamePrefix("LoadModelThread-");
        executor.initialize();
        return executor;
    }

}
