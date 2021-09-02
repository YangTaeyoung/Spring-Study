package com.example.put.controller;

import com.example.put.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {
    @PutMapping("/put/{userId}")
    public PostRequestDto put(@RequestBody PostRequestDto postRequestDto, @PathVariable long userId){
        System.out.println(postRequestDto);
        System.out.println("id:"+userId);
        // Response는 어떻게 하는가?
        // RestController인 경우 인스턴스를 반환하면 저절로 Json형태로 바꾸어 응답해줌
        return postRequestDto;
    }
}
