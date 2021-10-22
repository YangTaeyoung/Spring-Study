package com.fastcampus.jpa.bookmanager.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@RequiredArgsConstructor // 필요하다고 지정한 엘리먼트들만 인자로 받는 생성자를 생성 @NonNull로 설정하면 필수 인자가 된다.
@AllArgsConstructor
@Data
@Builder // Build 형식을 사용해 클래스를 만들 때 사용
@Entity
public class User {
    @Id
    @GeneratedValue // 자동으로 키가 증가하도록 설정.
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
