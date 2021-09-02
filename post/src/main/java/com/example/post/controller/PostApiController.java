package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {

//    @PostMapping("/post")
//    public void post(@RequestBody Map<String, Object> requestData) {
//        // Get방식일 때는 Map앞에 @RequestParam을 붙여야 하지만, Post방식의 경우 RequestBody라고 붙여야 한다.
//        requestData.forEach((key, value) -> {
//            System.out.println("key: " + key);
//            System.out.println("value : " + value);
//        });
//    }
    @PostMapping("/post")
    // Map말고 DTO를 사용하여 받을 수 있음
    public void post(@RequestBody PostRequestDto postRequestDto) {
        System.out.println(postRequestDto);
    }

}
