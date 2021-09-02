package com.example.ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

// 매개변수로 컴포넌트의 이름을 직접 지정해줄 수 있다.
@Component("base74Encoder")
public class Base64Encoder implements IEncoder {
    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
