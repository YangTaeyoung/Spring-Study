package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<구현할 엔티티 클래스, pk 클래스>: JPA를 구현하는 인터페이스
public interface UserRepository extends JpaRepository<User, Long> {

}
