package com.example.jpa.domain.post.post.service;

import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post write(String title, String body) {

        // 1. Post 조립
        Post post = Post.builder()
                .title(title)
                .body(body)
                .build();

        // 2. repository 에 넘김
        return postRepository.save(post);
    }

    public Post modify(Post post, String newTitle, String newBody) {
        post.setTitle(newTitle);
        post.setBody(newBody);

        return postRepository.save(post);
    }

    public long count() {
        return postRepository.count();
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }
}
