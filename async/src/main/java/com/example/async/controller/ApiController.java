package com.example.async.controller;

import com.example.async.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    private final AsyncService asyncService;

    @GetMapping("/hello")
    public String hello(){
        asyncService.hello();
        log.info("method end");
        return "hello";
    }
}
