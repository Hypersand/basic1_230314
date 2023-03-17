package com.ll.basic1.article.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Article {
    @Id  // Primary Key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO INCREMENT
    private long id;
    private LocalDateTime createDateTime; // 데이터 생성 날짜
    private LocalDateTime modifyDateTime; // 데이터 수정 날짜
    private String title;
    private String body;

}
