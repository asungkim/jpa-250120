package com.example.jpa.domain.tag.entity;

import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag extends BaseEntity {

    @Column(length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

}