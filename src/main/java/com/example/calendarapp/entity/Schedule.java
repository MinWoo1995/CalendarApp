package com.example.calendarapp.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String username;
    private String password;
    @CreatedDate
    @Column(updatable = false)//생성일은 수정될수 없다
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Schedule(String title, String content, String username, String password) {
        //id는 DB에서 할당
        this.title = title;
        this.content = content;
        this.username = username;
        this.password = password;
        //생성자에 날짜와 시간을 수동으로 설정해줄 필요가 없다.(JPA Auditing이 해줌)
//        @LocalDateTime
//        this.createAt = LocalDateTime.now();
//        @LocalDateTime
//        this.updateAt = LocalDateTime.now();
    }

    //업데이트는 프레임워크에게 맡기는게 아닌, 개발자의 의도와 더티체킹, 캡슐화 목적으로 업데이트 메서드는 직접 구현
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
