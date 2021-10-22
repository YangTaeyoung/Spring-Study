package com.fastcampus.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class UserTest {

    @Test
    void test(){
        User user = new User();
        user.setEmail("0130yang@gmail.com");
        user.setName("Taeyoung Yang");
        System.out.println(">>> "+ user);


    }
}