package com.fastcampus.jpa.bookmanager.domain.listener;


import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public class MyEntityListener {

    //  # 매개변수가 오브젝트인 이유
    // > 일반적으로 PrePersist같은 경우 this값을 사용할 수 있으나
    // 리스터 클래스에서는 Book가 올 수도 있고 User가 올 수도 있기 때문에
    // 어떤 것을 취해야 할 지에 대해 애매함.
    @PrePersist
    public void prePersist(Object o){
        if(o instanceof Auditable){
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
