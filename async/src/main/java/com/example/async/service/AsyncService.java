package com.example.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {
    public CompletableFuture run() {
        return new AsyncResult(hello()).completable();
    }

    @Async("async-thread") // 비동기로 실행하고자 하는 메서드에 추가
    public String hello(){
        for(int i = 0; i < 10; i++){
            try {
                Thread.sleep(2000);
                log.info("Thread Sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return "async hello";
    }

}
