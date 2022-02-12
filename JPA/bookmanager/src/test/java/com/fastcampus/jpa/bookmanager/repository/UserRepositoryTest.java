package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Gender;
import com.fastcampus.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest // SpringContext를 로드해서 테스트에 활용하겠다는 것.
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;
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
//        // PageRequest.of(page번호, 한 페이지할 때 나누는 크기)
//        Page<User> users = userRepository.findAll(PageRequest.of(0, 3));
//
//        System.out.println("page: " + users);
//        System.out.println("Total Elements: " + users.getTotalElements()); // 총 elements의 수
//        System.out.println("Total Pages:" + users.getTotalPages()); // 총 페이지의 수
//        System.out.println("numberOfElements: " + users.getNumberOfElements()); // 페이지 안에 들어있는 elements의 수
//        System.out.println("sort: " + users.getSort()); // sort된지의 여부
//        System.out.println("size: " + users.getSize()); // 페이징 할 때 나누는 크기
//        users.getContent().forEach(System.out::println);

//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("name") // name 속성은 무시함
//                .withMatcher("email", endsWith()) // endWith: 지정한 예시로 끝나는 행 검색 없으면 정확히 일치하는지 봄.
//                .withMatcher("email", contains()); // contain: 지정된 예시로 끝나는 컬럼이 포함하는지 확인
//        Example<User> example = Example.of(new User("ma","fastcampus.co.kr"), matcher);
//        // matcher없이 example만 만들면 정확히 매치하는지만 확인함.
//        userRepository.findAll(example).forEach(System.out::println);

//        userRepository.save(new User("David", "david@fastcampus.co.kr"));
//
//        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
//        user.setEmail("martin-updated@fastcampus.co.kr");
//        userRepository.save(user);
//

    }

    @Test
    void select() {
//        System.out.println(userRepository.findByName("martin"));
//        String target = "martin@fastcampus.co.kr";
//        // 모두 동일한 select쿼리를 만들어줌.
//        System.out.println("findByEmail: " + userRepository.findByEmail(target));
//        System.out.println("getByEmail: " + userRepository.getByEmail(target));
//        System.out.println("readByEmail: " + userRepository.readByEmail(target));
//        System.out.println("queryByEmail: " + userRepository.queryByEmail(target));
//        System.out.println("searchByEmail: " + userRepository.searchByEmail(target));
//        System.out.println("streamByEmail: " + userRepository.streamByEmail(target));
//        System.out.println("findUserByEmail: " + userRepository.findUserByEmail(target));
//
//        // 동일한 쿼리를 만들어줌.
//        System.out.println("findTop1ByName: "+ userRepository.findTop2ByName("martin"));
//        System.out.println("findFirst1ByName" + userRepository.findFirst1ByName("martin"));
//
//        System.out.println("findByEmailAndName: " + userRepository.findByEmailAndName(target,"martin"));
//        System.out.println("findByEmailOrName: " + userRepository.findByEmailOrName(target,"dennis"));
//        System.out.println("findByCreatedAtAfter:" + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusMonths(1L)));
//        System.out.println("findByIdAfter: " + userRepository.findByIdAfter(4L));
//        System.out.println("findByCreatedAtGreaterThan: " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
//        System.out.println("findByCreatedAtGreaterThanEqual: " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));

//        System.out.println("findByCreatedAtBetween: " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now()));
//        System.out.println("findByIdBetween: "+ userRepository.findByIdBetween(1L,3L));
//        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual: " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));
//        System.out.println("findByIdIsNotNull: " + userRepository.findByIdIsNotNull());
//        System.out.println("findByAddressIsEmpty: " + userRepository.findByAddressIsNotEmpty());
//        System.out.println("findByNameIn: " + userRepository.findByNameIn(Lists.newArrayList("martin", "dennis")));
//        System.out.println("findByNameStartingWith: " + userRepository.findByNameStartingWith("mar"));
//        System.out.println("findByNameEndingWith: " + userRepository.findByNameEndingWith("tin"));
//        System.out.println("findByNameContaining: " + userRepository.findByNameContaining("art"));
//        System.out.println("findByNameLike: " + userRepository.findByNameLike("%art%"));
    }

    @Test
    void pagingAndSortingTest() {
//        System.out.println("findTop1ByName:" + userRepository.findTop1ByName("martin"));
//        // Last1과 같은 키워드는 무시되어 FindByName 함수에 해당하는 쿼리를 실행하게 된다.
//        System.out.println("findLast1ByName:" + userRepository.findLast1ByName("martin"));
//        // Top1에서 1을 없애도 저절로 1개만 가져오게 됨.
//        System.out.println("findTop1ByNameOrderByIdDesc: " + userRepository.findTop1ByNameOrderByIdDesc("martin"));
//        System.out.println("findFirstByNameOrderByIdDescEmailAsc: " + userRepository.findFirstByNameOrderByIdDescEmailAsc("martin"));
//        System.out.println("findFirstByNameWithSortParams: " + userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));

        // 1개 단위로 나누고 0페이지를 가져오는데 정렬 기준은 id의 역순
        System.out.println("findByNameWithPaging: " + userRepository.findByName(
                        "martin",
                        PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")))
                ).getContent()
        );
    }

    @Test
    void insertAndUpdateTest() {
        User user = new User();
        user.setName("martin");
        user.setEmail("martin2@fastcampus.co.kr");
        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrrtin");
        userRepository.save(user2);
    }

    @Test
    void enumTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);
        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);
        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    @Test
    void listenerTest() {

        // Insert
        User user = new User();
        user.setEmail("martin@fastcampus.com");
        user.setName("martin");
        userRepository.save(user);

        // Select
        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        // Update
        user2.setName("marrrrrtin");
        userRepository.save(user2);

        // Delete
        userRepository.deleteById(4L);
    }

    @Test
    void prePersistTest() {
        User user = new User();
        user.setEmail("0130yang@gmail.com");
        user.setName("양태영");
        // 아래의 값을 계속해서 세팅해주는 경우는 Don't Repeat Yourself: DRY법칙에 어긋나며, 개발자가 라인을 빼먹을 경우
        // 시스템이 오동작할 수 있음.
        // 해당 경우와 같은 경우 EntityListener를 통해 insert전에 값을 세팅해 주는 것이 권장됨.
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        System.out.println(userRepository.findByEmail("0130yang@gmail.com"));
    }

    @Test
    public void preUpdateTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println("as_is: " + user);
        user.setName("martin2");
        userRepository.save(user);

        System.out.println("to-be:" + userRepository.findAll().get(0));
    }

    @Test
    public void userHistoryTest(){
        User user = new User();
        user.setEmail("hello@hello.com");
        user.setName("hello");
        userRepository.save(user);

        user.setName("hello-new");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }
}
