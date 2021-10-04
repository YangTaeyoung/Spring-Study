package com.example.swagger.controller;

import com.example.swagger.dto.UserReq;
import com.example.swagger.dto.UserRes;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"API 정보를 제공하는 Controller"})
@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "x값", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "y", value = "y값", required = true, dataType = "int", paramType = "query")
    }) // 2. 이렇게 설명에 필요한 것을 한꺼번에 지정할 수도 있음.
    @GetMapping("/plus/{x}")
    public int plus(
            @ApiParam(value = "x값") // 1. 따로 따로 설명을 적는다면 코드가 보기 불편해질 수 있다.
            @PathVariable int x,
            @ApiParam(value = "y값")
            @RequestParam int y
    ) {
        return x + y;
    }

    @ApiResponse(code = 502, message = "사용자이 나이가 10살 이하일 때")
    @ApiOperation(value = "사용자의 이름과 나이를 리턴하는 메서드") // api 뒤에 설명을 붙임
    @GetMapping("/user")
    public UserRes user(UserReq userReq) {
        return new UserRes(userReq.getName(), userReq.getAge());
    }

    @PostMapping("/user")
    public UserRes userPost(@RequestBody UserReq userReq) {
        return new UserRes(userReq.getName(), userReq.getAge());
    }
}
