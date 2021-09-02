package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// RestController과는 달리 HTML 페이지 리소스를 찾음.
@Controller
public class PageController {
    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    // 일반적으로 @Controller 어노테이션을 클래스에 붙이면 html 리소스를 찾으나,
    // 메소드에 @ResponseBody 어노테이션을 붙일 경우 Json을 리턴하게 할 수 있음.
    @ResponseBody
    @GetMapping("/user")
    public User user(){
        // var: 자바 11 버전에서는 타입 추론을 할 수 있는 약어가 추가됨.
        var user = new User();
        user.setName("steve");
        user.setAddress("패스트캠퍼스");
        return user;

    }
}
