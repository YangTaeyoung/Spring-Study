package com.example.async.controller;

import com.example.async.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    private final AsyncService asyncService;

    @GetMapping("/hello")
    public CompletableFuture hello(){
        // CompletableFuture 다른 스레드에서 실행시키고 결과를 반환받는 형태
        // 여러개의 api를 동시에 전송을 하고 결과를 조인해서 받을 때 좋다고 함.
        asyncService.hello();
        log.info("CompletableFuture init");
        return asyncService.run();
    }
}
