package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    // GetMapping: GET 방식으로 연결하고자 할 때 사용함
    // path = 을 통해 path를 직접 입력할 수 있고, path = 을 생략해도 됨.
    @GetMapping("/hello")
    public String hello() {
        return "hello spring boot!";
    }

    // RequestMapping: 모든 방식이 연결을 허용할 때 사용함.
    // method = : 을 통해 연결 방식을 정의해 줄 수 있음
    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public String hi() {
        return "hi";
    }

    // 패스 파라미터를 사용할 때 사용하는 것.
    // 중괄호로 감싸고 안에 변수 이름을 적는다.
    // 보통 매개변수로 @PathVariable에 선언된 변수와 이름이 같아야 하나
    // PathVariable(name = URL속 변수이름)과 이름을 맞추면 변수 명을 다르게 매핑할 수 있다.
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable(name = "name") String pathName) {
        // @PathVariable: 가변적인 값을 주소로 사용할 때 사용하는  어노테이션
        System.out.println("PathVariable: " + pathName);
        return pathName;
    }

    // Query Parameter: search?q=ahahah 와 같이 값을 받는 url 방식
    @GetMapping("/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {
        // RequestParam: Query Parameter을 받기 위해서 사용하는 어노테이션.
        StringBuilder sb = new StringBuilder();
        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");
            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        });
        return sb.toString();
    }

    // 명확하게 쿼리 파라미터에 키 값을 정해놓을 경우
    @GetMapping("/query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ) {
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name + " " + email + " " + age;
    }

    // @RequestParam을 적어서 매번 파라미터를 받으면 굉장히 번거로움.
    // 그런 것들을 해소하고자 직접 클래스를 매개변수로 넣어서 처리할 수 있음.
    // 이때 RequestParam 어노테이션은 붙이지 않음.
    @GetMapping("/query-param03")
    public String queryParam03(UserRequest userRequest) {
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());
        return userRequest.toString();
    }
}
