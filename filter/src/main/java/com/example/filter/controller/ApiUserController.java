package com.example.filter.controller;

import com.example.filter.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/temp")
public class ApiUserController {
    @PostMapping("")
    public User user(@RequestBody User user){
        // log.info: lombok이 제공하는 로깅 기능, 중괄호 안에 뒤에 적은 객체가 매칭되어 들어간다.
        log.info("User : {}", user);
        return user;
    }

}
