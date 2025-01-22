package com.example.jpa.global;

import com.example.jpa.domain.post.comment.entity.Comment;
import com.example.jpa.domain.post.comment.service.CommentService;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    @Lazy
    private BaseInitData self;

    @Bean
    @Order(1)
    public ApplicationRunner applicationRunner() {
        return args -> {
            self.work1();
            self.work2();
        };
    }

    @Transactional
    public void work2() {

    }

    @Transactional
    public void work1() {
        if (postService.count() > 0) {
            return;
        }
        Post p1 = postService.write("title1", "content1");
        Post p2 = postService.write("title2", "content2");
        Post p3 = postService.write("title3", "content3");
        Post p4 = postService.write("title1", "content1");
        Post p5 = postService.write("title1", "content1");

        Comment c1 = Comment.builder().body("comment1").build();
        p1.addComment(c1); // 관계의 주인이 DB 반영을 한다

        Comment c2 = Comment.builder().body("comment2").build();
        p1.addComment(c2);

        Comment c3 = Comment.builder().body("comment3").build();
        p1.addComment(c3);

//        p1.removeComment(c1);

    }
}
