package com.ll.basic1.boundedContext.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = IDENTITY)  // AUTO_INCREMENT
    private long id;
    private String title;
    private String body;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

}