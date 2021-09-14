package com.example.filter.dto;

import lombok.*;

//@Getter
//@Setter
@NoArgsConstructor // 기본 생성자를 만들어 줌
@AllArgsConstructor // 모든 변수를 받는 생성자를 만들어줌.
@Data // 필요한 함수를 거의 만들어줌.
public class User {
    private String name;
    private int age;
}
