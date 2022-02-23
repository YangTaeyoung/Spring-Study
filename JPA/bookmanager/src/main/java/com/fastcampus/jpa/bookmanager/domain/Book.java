package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long autherId;

    private Long pulisherId;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude // 순환 참조를 방지하기 위한 옵션
    private BookReviewInfo bookReviewInfo;

//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

//    @PrePersist
//    public void prePersist(){
//        this.setCreatedAt(LocalDateTime.now());
//        this.setUpdatedAt(LocalDateTime.now());
//    }
//    @PreUpdate
//    public void preUpdate(){
//        this.setUpdatedAt(LocalDateTime.now());
//    }
}
