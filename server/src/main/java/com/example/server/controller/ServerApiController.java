package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/naver")
    public String naver(){
        String query = "갈비집";
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", query)
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode(StandardCharsets.UTF_8) // 인코딩 셋을 지정할 수 있게 함 default는 UTF-8. Charset.forName("UTF-8")로도 지정이 가능하다.
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        // RequestEntity: 헤더를 설정해서 보낼 때, 아니면 post형식으로 json을 요청과 함께 보내고 싶을 때 사용한다.
        // RequestEntity<제네릭>의 제네릭 부분에는 헤더를 실어 보낼 때 사용할 클래스를 지정하는 것 같다.
        // 만약 User를 보내고 싶으면 RequestEntity<User>로 설정하고 .get이 아닌 .post로 설정한 후 .body(보낼 객체)를 보내주면 된다.
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("x-Naver-Client-Id","9JWI6ruL74CEVqgkzXzE")
                .header("x-Naver-Client-Secret", "FJYLc07Kas")
                .build();
        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        return result.getBody();
    }

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age) {
        User user = new User(name, age);
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(@RequestBody Req<User> user,
                     @PathVariable int userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header") String customerHeader
    ){
        log.info("userId: {}, userName: {}", userId, userName);
        log.info("authorization: {}, customerHeader: {}", authorization, customerHeader);
        log.info("client req: {}", user);
        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header()
        );
        response.setResBody(
                user.getResBody()
        );
        return response;
    }
}
