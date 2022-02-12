package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

// JpaRepository<구현할 엔티티 클래스, pk 클래스>: JPA를 구현하는 인터페이스
public interface UserRepository extends JpaRepository<User, Long> {
    // 쿼리 메소드: 키워드를 통해 추가해주면 해당 기준의 속성에 맞게 쿼리 메서드를 실행시킬 수 있음.
    // 고정된 형태의 객체를 받는 것이 아닌, 여러 형태의 객체를 반환타입으로 받을 수 있다.
    // 1개가 존재하는지, 여러개가 존재하는지에 따라 다름.
    // Optional<User> findByName(String name);
    // Set<User> findByName(String name);
    // User findByName(String name);
    List<User> findByName(String name); // 이름을 기준으로 조회하는 것 반환: 리스트 형태
    // 코드의 가독성을 높이기 위해 Is키워드나 Equals 키워드를 사용할 수 있음.
    // 아래의 세 코드는 동일한 역할을 수행한다.
    Set<User> findUserByNameIs(String name);
    Set<User> findUserByName(String name);
    Set<User> findUserByNameEquals(String name);

    User findByEmail(String email);
    User getByEmail(String email);
    User readByEmail(String email);
    User queryByEmail(String email);
    User searchByEmail(String email);
    User streamByEmail(String email);
    User findUserByEmail(String email);

    // 위와 똑같이 동작함. 이런식으로 네이밍 하지 않도록 주의해야 함
    // User findSomethingByEmail(String email);
    // countBy, removeBy, deleteBy 와 같은 메서드는 쿼리 메서드를 정의하기 보다는
    // JpaRepository에서 제공하는 메서드를 사용하는 것이 일반적이다.

    // First<numeric> Top<numeric>과 같은 메서드는 여러 요소가 리턴될 때 첫번째를 리턴한다.
    List<User> findFirst1ByName(String name); // 위에서부터 1개 가져옴
    List<User> findTop2ByName(String name); // 위에서부터 2개 가져옴

    // Last1과 같은 키워드는 쓸 수 없음.
    List<User> findLast1ByName(String name);
    // orderBy와 같은 메서드를 이용한 다음 First를 사용하곤 함.

    // AND OR
    // where 조건에 AND조건과 OR조건을 추가하여 사용할 수 있음.
    List<User> findByEmailAndName(String email, String name);
    List<User> findByEmailOrName(String email, String name);

    // After, Before를 통해 대소 비교를 사용할 수 있음.
    // 단 이는 JpaRepository가 가지는 유연함일 뿐, GreaterThan, LessThan와 같은 대소비교 키워드를 쓰는 것이 훨씬 적적함.
    List<User> findByCreatedAtAfter(LocalDateTime createdAt);
    List<User> findByIdAfter(Long id);

    // 실제 대소비교를 할 때 잘 사용하는 키워드
    List<User> findByCreatedAtGreaterThan(LocalDateTime createdAt);
    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime createdAt);

    // 사이에 껴 있는지 확인할 수 있음. (param1 <= elem <= param2)
    List<User> findByCreatedAtBetween(LocalDateTime localDateTime1, LocalDateTime localDateTime2);
    List<User> findByIdBetween(Long id1, Long id2);
    // Equal이 포함되느냐 안되느냐에 따라 쿼리 결과 값이 달라짐, 해당 부분의 경우 Than뒤에 Equal이 모두 붙음으로써 between과 동일하게 동작함을 알 수 있음.
    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);
    // 널 값이 아닌지 확인 가능
    List<User> findByIdIsNotNull();

    // 사용 불가능 Empty는 collection타입의 element만 사용할 수 있다.
    // String에서의 Empty는 ""과 같은 빈 문자열을 의미함
    // 따라서 일반적으로 IsEmpty는 문자열이 널이거나 빈 문자열이거나를 체크한다고 생각할 수 있으나,
    // Spring JPA Query Method에서는 외래키로 조회되는 1:n이나 n:n관계에서의 관계가 있는 테이블이
    // 비어있는지를 조회하는 메서드임.
    // 일반적으로 잘 사용되지 않음 경고도 뜨는 것을 보면.
    List<User> findByAddressIsNotEmpty();

    // SQL에서 IN절에 해당하는 구문
    List<User> findByNameIn(List<String> names);

    // 문자열에 대한 조회와 관련 있는 메서드
    // SQL의 Like절과 유사함
    // 1. name으로 시작하는가?
    List<User> findByNameStartingWith(String name);
    // 2. name으로 끝나는가?
    List<User> findByNameEndingWith(String name);
    // 3. name을 포함하는가?
    List<User> findByNameContaining(String name);
    // 4. Like절을 사용자 단에서 직접 입력하도록 정의할 수 있음.
    List<User> findByNameLike(String name);

    List<User> findTop1ByName(String name);

    // 이름을 통해 요소를 가져오되 id를 기준으로 내림차순으로 정렬한 후 위에서 한 요소를 선택
    List<User> findTop1ByNameOrderByIdDesc(String name);

    // 정렬 기준을 여러개 하기
    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);

    // 정렬 기준을 파라미터를 통해 넘겨줄 수 있다.
    List<User> findFirstByName(String name, Sort sort);

    // 페이징 처리하여 엔티티를 조회할 수 있다.
    // 페이지 객체는 페이지 총 수, 현재 페이지 등에 대한 정보를 추가적으로 제공하고 있음.
    Page<User> findByName(String name, Pageable pageable);

    @Query(value = "select * from user limit 1;", nativeQuery = true)
    Map<String, Object> findRawRecord();
}
