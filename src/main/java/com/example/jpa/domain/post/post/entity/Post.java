package com.example.jpa.domain.post.post.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Post {

    @Id // PR
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO-INCREMENT
    private long id;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @Column(length = 100)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;
}
