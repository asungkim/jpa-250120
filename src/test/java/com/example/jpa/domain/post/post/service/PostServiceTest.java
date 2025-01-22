package com.example.jpa.domain.post.post.service;

import com.example.jpa.domain.post.post.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
public class PostServiceTest {

    @Autowired
    private PostService postService;


    @Test
    @DisplayName("글 2개 작성")
    @Transactional
    @Rollback(value = true)
    public void t1() {
        postService.write("title1","body1");
        postService.write("title2","body2");

    }

    @Test
    @DisplayName("모든 글 조회")
    public void t2() {
        List<Post> all = postService.findAll();
        assertThat(all.size()).isEqualTo(5);

        Post p = all.get(0);
        assertThat("title1").isEqualTo(p.getTitle());
    }


    @Test
    @DisplayName("특정 글 조회")
    public void t3() {
        Optional<Post> opPost = postService.findById(1L);

        if (opPost.isPresent()) {
            assertThat(opPost.get().getTitle()).isEqualTo("title1");
        }


    }

    @Test
    @DisplayName("제목으로 조회")
    public void t4() {
        List<Post> posts=postService.findByTitle("title1");

        assertThat(posts.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("제목과 내용으로 조회")
    public void t5() {
        List<Post> posts=postService.findByTitleAndBody("title1","content1");

        assertThat(posts.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("제목이 포함된 결과 조회")
    public void t6() {
        List<Post> posts=postService.findByTitleLike("title%");

        assertThat(posts.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("아이디 순으로 내림차순 정렬")
    public void t7() {
        List<Post> posts=postService.findByOrderByIdDesc();
        assertThat(posts.get(0).getId()).isEqualTo(5);

    }

    @Test
    @DisplayName("위에서 2개만 조회")
    public void t8() {
        // select * from post where title=? order by id desc limit 2;
        List<Post> posts=postService.findTop2ByTitleOrderByIdDesc("title1");

        // title1 id -> 1 4 5
        assertThat(posts.get(0).getId()).isEqualTo(5);
        assertThat(posts.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("findAll(Pageable)")
    public void t9() {
        // select * from post like 2,2

        int itemsPerPage=2;
        int pageNumber=2;
        pageNumber--;
        Pageable pageable = PageRequest.of(pageNumber, itemsPerPage, Sort.by(Sort.Direction.DESC, "id"));
        // id 5 4 | (3 2) | 1
        Page<Post> postPage = postService.findAll(pageable);
        List<Post> posts = postPage.getContent(); //

        assertThat(posts.size()).isEqualTo(2);

        assertThat(posts.get(0).getId()).isEqualTo(3);
        assertThat(posts.get(1).getId()).isEqualTo(2);
    }

}