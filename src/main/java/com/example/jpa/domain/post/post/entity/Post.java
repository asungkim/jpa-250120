package com.example.jpa.domain.post.post.entity;

import com.example.jpa.domain.post.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Post {

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

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;


    // mapped -> comments 안에 comment 는 post가 관리할거야
    // cascade -> post가 사라지면 comment 어떻게 할거야?
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
