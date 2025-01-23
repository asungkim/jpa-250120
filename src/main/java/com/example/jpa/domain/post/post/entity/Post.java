package com.example.jpa.domain.post.post.entity;

import com.example.jpa.domain.member.entity.Member;
import com.example.jpa.domain.post.comment.entity.Comment;
import com.example.jpa.domain.tag.entity.Tag;
import com.example.jpa.domain.tag.entity.TagId;
import com.example.jpa.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Post extends BaseTime {

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;


    // cascade -> post가 사라지면 comment 어떻게 할거야?
    // orphanRemoval -> 부모를 삭제하면 자식을 삭제할꺼야?
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();


    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
    }

    public void addTag(String name) {

//        Optional<Tag> oldTag = tags.stream()
//                .filter(tag -> tag.getName().equals(name))
//                .findFirst();
//
//        if (oldTag.isPresent()) {
//            return;
//        }

        Tag tag = Tag.builder()
                .id(new TagId(this.getId(),name))
                .post(this)
                .build();
        this.tags.add(tag);
    }
}
