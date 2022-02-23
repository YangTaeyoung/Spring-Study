package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import com.fastcampus.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@RequiredArgsConstructor // 필요하다고 지정한 엘리먼트들만 인자로 받는 생성자를 생성 @NonNull로 설정하면 필수 인자가 된다.
@AllArgsConstructor
@Data
@Builder // Build 형식을 사용해 클래스를 만들 때 사용
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
// 테이블에 관한 속성을 명시 할 수 있다.
//@Table(
//        name = "user",
//        indexes = {@Index(columnList = "name")},
//        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
//)
// 사용할 EntityListener 클래스 설정.
@EntityListeners(value = {UserEntityListener.class})
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 키가 증가하도록 설정.
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String email;

    // 속성의 값으로 열거형을 사용할 수 있음.
    // 이때 스프링 내부에서는 열거형에 있는 값이 MALE, FEMALE 등으로 되어 있더라도
    // 숫자의 형태로 바꾸어 0, 1 번으로 각각 저장하고 가져올 때 해당 값들을 각각 MALE, FEMALE에
    // 저장하여 내보내게 됨.
    // 그런데 이경우 열거형에 새로운 변수를 앞에 BABY라고 추가하면 기존에 0번이었던 MALE이 BABY로
    // 바꾸어져 출력이 될 수 있음.
    // 따라서 숫자로 저장되는 불상사를 방지하기 위해서 value = EnumType.STRING으로 바꾸어 사용하여야 함.
    // 기본 타입은 ORDINAL로 번호 순으로 저장되게 설정되어있음.
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    // DDL 쿼리에 해당 속성이 not null인채로 생성되게 된다.
    // @Column(nullable = false)
//    @Column(updatable = false) // 업데이트 시에 해당 컬럼은 사용하지 않도록 설정
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    // @Column(insertable = false) // 삽입 시에 해당 컬럼은 사용하지 않도록 설정
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.EAGER) // 1대 n관계일 때 사용.
    private List<Address> address;

    // @Transient: DB와는 상관없는 오브젝트 속성을 넣어야 할 때 사용하는 어노테이션
    @Transient // 영속성 처리에서 제외돼서 반영됨.
    private String testData;

//    // insert 메서드 실행 전
//    @PrePersist
//    public void prePersist(){
//        this.setUpdatedAt(LocalDateTime.now());
//        this.setCreatedAt(LocalDateTime.now());
//    }
    // update 메서드 실행 전
//    @PreUpdate
//    public void preUpdate(){
//        this.setUpdatedAt(LocalDateTime.now());
//    }
//    // remove 메서드 실행 전
//    @PreRemove
//    public void preRemove(){
//        System.out.println(">>> preRemove");
//    }
//    // insert 메서드 실행 후
//    @PostPersist
//    public void postPersist(){
//        System.out.println(">>> postPersist");
//    }
//    // update 메서드 실행 후
//    @PostUpdate
//    public void postUpdate(){
//        System.out.println(">>> postUpdate");
//    }
    // remove 메서드 실행 후
//    @PostRemove
//    public void postRemove(){
//        System.out.println(">>> postRemove");
//    }
    // select 조회가 일어난 직후에 실행
//    @PostLoad
//    public void postLoad(){
//        System.out.println(">>> postLoad");
//    }
 }
