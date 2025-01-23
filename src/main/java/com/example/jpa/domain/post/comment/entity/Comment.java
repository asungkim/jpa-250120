package com.example.jpa.domain.post.comment.entity;

import com.example.jpa.domain.member.entity.Member;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Comment extends BaseEntity {
    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    // fetch -> 언제 post의 정보가 필요한가요? -> LAZY = 필요할때 주세요
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id") // post_id 라는 외래키 컬럼이 생성됨
    private Post post;
}
