package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest // SpringContext를 로드해서 테스트에 활용하겠다는 것.
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() { // created, read, update, delete
        // List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name")); // 이름순 내림차순 정렬
        // List<User> users = userRepository.findAllById(Lists.newArrayList(1L,3L,5L));
        // findAllById (리스트 변수): 리스트에 지정된 id를 갖는 엔티티만 가져옴
        // Lists.newArrayList(요소1,요소2,요소3 ...) 파라미터를 요소로 갖는 리스트 변수를 반환함
        // users.forEach(System.out::println);

//
//        User user1 = new User("양태영", "0130yang@gmail.com");
//        User user2 = new User("김서영", "tjdud4302@naver.com");
//        // 리스트에 담긴 모든 엔티티를 저장하는 함수
//        userRepository.saveAll(Lists.newArrayList(user1,user2));
//        // 5번 인덱스를 가진 요소를 가져온다. 단 세션이 호출 후 죽기 때문에 부모 함수에
//        // @Transactional 어노테이션을 붙여야 한다.
//        // User user3 = userRepository.getOne(5L);
//
//        // findById: pk를 기준으로 5인 것을 찾아옴. Optional<받을 객체 클래스> 인스턴스로 반환
//        // orElse: 검색 결과가 없는 경우 null을 반환함.
//        User user = userRepository.findById(5L).orElse(null);
//        // 하나만 저장할 수도 있음
//        userRepository.save(user1);
//
//        List users = userRepository.findAll();
//        users.forEach(System.out::println);

        // 인덱스 1번을 가진 엔티티가 존재하는지 확인 출력: 참 거짓.
        // boolean exists = userRepository.existsById(1L);
//
//        // delete(객체): 해당 객체가 있을 경우 삭제함.
//        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
//        // deleteById: 해당 인덱스를 가진 객체가 있을 경우 삭제함
//        userRepository.deleteById(1L);

//        // 모두 다 지움
//        userRepository.deleteAll();
//        // 리스트에 해당 하는 것만 지울 수 있음. --> 성능 이슈가 존재할 수 있음 (조회를 먼저 함)
//        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L,2L,3L)));
//        delete 쿼리를 한번 사용함으로써 성능이슈를 줄일 수 있음.
//        userRepository.deleteAllInBatch(userRepository.findAllById(Lists.newArrayList(1L,2L)));
//        // 이게 더 좋을 듯
//        userRepository.deleteAllById(Lists.newArrayList(1L,2L,3L));
//
//        userRepository.findAll().forEach(System.out::println);

//        // # 페이징
//        // 스프링은 페이징을 지원함.
//        Page<User> users = userRepository.findAll(PageRequest.of(0, 3));
//
//        System.out.println("page: " + users);
//        System.out.println("Total Elements: " + users.getTotalElements()); // 총 elements의 수
//        System.out.println("Total Pages:" + users.getTotalPages()); // 총 페이지의 수
//        System.out.println("numberOfElements: " + users.getNumberOfElements()); // 페이지 안에 들어있는 elements의 수
//        System.out.println("sort: " + users.getSort()); // sort된지의 여부
//        System.out.println("size: " + users.getSize()); // 페이징 할 때 나누는 크기
//        users.getContent().forEach(System.out::println);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name") // name 속성은 무시함
                .withMatcher("email", endsWith())
                .wit

    }
}