package com.example.jpa.global.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BaseEntity {
    @Id // PR
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO-INCREMENT
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Long id;

}
