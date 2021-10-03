package com.example.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AppConfig {
    @Bean("async-thread")
    public Executor asyncThread(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100); //
        threadPoolTaskExecutor.setCorePoolSize(10); // 기본적으로 열 개만 쓰도록
        threadPoolTaskExecutor.setQueueCapacity(10); // 열개를 다 사용하고 있는 경우 큐에 10개까지 대기시킴
        // 그러다 다시 차면 10개가 다시 늘어나서 100개까지 늘어남.
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");
        return threadPoolTaskExecutor;
    }
}
