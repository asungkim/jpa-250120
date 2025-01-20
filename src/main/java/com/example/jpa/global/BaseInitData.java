package com.example.jpa.global;

import com.example.jpa.domain.post.comment.entity.Comment;
import com.example.jpa.domain.post.comment.service.CommentService;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;
    private final CommentService commentService;

    @Bean
    @Order(1)
    public ApplicationRunner applicationRunner() {

        return args -> {
            if (postService.count() > 0) {
                return;
            }
            postService.write("title1", "content1");
            postService.write("title2", "content2");
            postService.write("title3", "content3");
        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner applicationRunner2() {
        return new ApplicationRunner() {
            @Override
            @Transactional
            public void run(ApplicationArguments args) throws Exception {
                Post post = postService.findById(1L).get();
                Thread.sleep(1000);
                postService.modify(post, "new title", "new content");
            }
        };
    }

    @Bean
    @Order(3)
    public ApplicationRunner applicationRunner3() {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {

                Post post = postService.findById(1L).get();

                if (commentService.count() > 0) {
                    return;
                }

                Comment c1 = commentService.write(post.getId(), "comment 1");
                Comment c2 = commentService.write(post.getId(), "comment 2");
                Comment c3 = commentService.write(post.getId(), "comment 3");

                // 1번 댓글에 대한 댓글 정보
                System.out.println(c1.getId() + "번 댓글의 부모 게시글 번호는 " + c1.getPostId() + "번 입니다");

                // 1번 댓글의 부모 게시물 제목 정보
                Post findPost = postService.findById(c1.getPostId()).get();
                System.out.println(c1.getId() + "번 댓글의 부모 게시물 제목은 " + findPost.getTitle());

            }
        };
    }
}
