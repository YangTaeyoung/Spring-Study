package com.example.response.controller;


import com.example.response.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    //text로 응답을 받는 방법. 실제로 그런 경우는 별로 없음.
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    // Json을 받는 방법: 가장 많이 사용함.
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user;
    } // 200 OK, 정상적인 연결 요청이나 수정되었을 때 응답 코드

    @PutMapping("/put")
    public ResponseEntity<User> put (@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    } // 201 -> 생성되었을 때 서버가 보내는 응답코드

}
