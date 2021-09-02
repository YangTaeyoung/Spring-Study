package com.example.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    // GetMapping: GET 방식으로 연결하고자 할 때 사용함
    // path = 을 통해 path를 직접 입력할 수 있고, path = 을 생략해도 됨.
    @GetMapping("/hello")
    public String hello() {
        return "hello spring boot!";
    }

}
