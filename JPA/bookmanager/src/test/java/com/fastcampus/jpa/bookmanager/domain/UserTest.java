package com.fastcampus.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class UserTest {

    @Test
    void test(){
        User user = new User();
        user.setEmail("0130yang@gmail.com");
        user.setName("Taeyoung Yang");
        System.out.println(">>> "+ user);

        //User user1 = new User(null, "martin", "martin@fastcampus.com", LocalDateTime.now(), LocalDateTime.now(), null);
        User user2 = new User("martin", "martin@fastcampus.com");
        User user3 = User.builder()
                .name("martin")
                .email("martin@fastcampus.co.kr")
                .build();

        System.out.println(">>> "+ user.toString());

    }
}