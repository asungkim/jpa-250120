package com.example.jpa.domain.post.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
public class Post {

    @Id // PR
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO-INCREMENT
    @Getter
    private Long id; // long -> null x , LOng -> null o
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @Column(length = 100)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;






}
