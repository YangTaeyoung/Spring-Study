package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class RestTemplateService {
    // http://localhost/api/server/hello에 요청해서 response를 가져올거임.
    public User hello(String name, int age) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", name)
                .queryParam("age", age)
                .encode()
                .build()
                .toUri();

        System.out.println(uri);

        // - RestTemplate
        // rest 관련 처리를 쉽게 하기 위해서 스프링에서 만들어 놓은 것
        // 요청과 받는 것에 대한 처리가 쉽다.
        // 타 api에서 데이터를 가져올 때 유용하게 사용할 듯 하다.
        RestTemplate restTemplate = new RestTemplate();

        // - getForEntity(요청할 uri, 가지고 올 데이터의 dto 클래스.class)
        // ResponseEntity<Body의 데이터 타입> 형태로 응답을 가져온다. Object와 달리 요청 응답 Body뿐만 아니라 추가 정보를 더 가지고 있다.
        // - getForObject(요청할 uri, 가지고 올 데이터의 dto 클래스.class)
        // Object형태로 응답을 가져온다. 응답 상태 등의 추가정보는 가지고 오지 못한다.
        ResponseEntity<User> result = restTemplate.getForEntity(uri, User.class);
        log.info("status code: {}", result.getStatusCode());
        log.info("body: {}", result.getBody());
        return result.getBody();
    }

    public User post() {
        // http://localhost:9090/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve") // 순서대로 중괄호 속 변수에 매칭된다.
                .toUri();
        log.info("request uri: {}", uri);
        // http body -> object mapper -> json -> rest template -> http
        User req = new User();
        req.setName("steve");
        req.setAge(10);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.postForEntity(uri, req, User.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
        return response.getBody();
    }

    public User exchange() {
        // http://localhost:9090/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve") // 순서대로 중괄호 속 변수에 매칭된다.
                .toUri();
        log.info("request uri: {}", uri);
        // http body -> object mapper -> json -> rest template -> http
        User req = new User();
        req.setName("steve");
        req.setAge(10);

        RequestEntity<User> requestEntity = RequestEntity // request 시 헤더에 값을 추가하여 보내고 싶을 때
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "ffffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.exchange(requestEntity, User.class);
        return response.getBody();
    }

    public Req<User> genericExchange() {
        // http://localhost:9090/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve") // 순서대로 중괄호 속 변수에 매칭된다.
                .toUri();
        log.info("request uri: {}", uri);
        // http body -> object mapper -> json -> rest template -> http
        User user = new User();
        user.setName("steve");
        user.setAge(10);
        Req<User> req = new Req();
        req.setHeader(
                new Req.Header()
        );
        req.setResBody(
                user
        );

        RequestEntity<Req<User>> requestEntity = RequestEntity // request 시 헤더에 값을 추가하여 보내고 싶을 때
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "ffffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();

        // 제네릭에 클래스는 사용할 수 없음
        // ParameterizedTypeReference<넣고 싶은 클래스>을 대신 사용
        // ResponseEntity에는 이미 받을 타입을 선언해 놓았기 때문에 ParameterizedTypeReference에는 제네릭을 생략해도 된다,
        ResponseEntity<Req<User>> response = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>() {});

        // responseEntity는 getBody를 함수로 가지고 있음. 그러나 Req도 getBody()를 갖고 있으므로 중복 호출 가능.
       return response.getBody();
    }
}
