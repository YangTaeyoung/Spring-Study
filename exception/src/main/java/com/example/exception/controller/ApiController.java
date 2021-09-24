package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api")
// - 들어오는 매개변수에 validation을 하고 싶으면 아래 두 가지 단계를 거쳐야 한다.
// ① 클래스에 @Validated 어노테이션을 붙인다.
@Validated
public class ApiController {
    // ② 검증을 원하는 파라미터 앞에 하고 싶은 validation 어노테이션을 붙인다.
    @GetMapping("/user")
    public User get(
            @Size(min = 2)
            @RequestParam String name,

            @NotNull
            @Min(1)
            @RequestParam Integer age
    ){
        User user = new User();
        user.setAge(age);
        user.setName(name);

        int a = 10+ age;

        return user;
    }

    @PostMapping("/user")
    public User post(@Valid @RequestBody User user){
        return user;
    }
}
