package com.example.jpa.domain.post.comment.entity;

import com.example.jpa.domain.post.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id // PR
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO-INCREMENT
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Long id; // long -> null x , LOng -> null o

    // created, modified 감시하기위해 EntityListeners
    @CreatedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime modifiedDate;

    @Column(columnDefinition = "TEXT")
    private String body;


    // fetch -> 언제 post의 정보가 필요한가요? -> LAZY = 필요할때 주세요
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id") // post_id 라는 외래키 컬럼이 생성됨
    private Post post;
}
