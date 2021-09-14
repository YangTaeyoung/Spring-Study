package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("-----------");
        // - object-mapper
        // > Text Json -> Object, Object -> Text Json

        var objectMapper = new ObjectMapper();

        // object -> text(getter메서드가 필요함, 단 멤버변수만 get메서드를 사용하여야만 한다.)
        var user = new User("steve", 10,"010-9691-1648");

        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);
        // text -> object(디폴트 생성자가 필요함)
        var objectUser = objectMapper.readValue(text, User.class);
        System.out.println(objectUser);
    }

}
