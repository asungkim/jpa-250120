package com.example.jpa.domain.post.post.service;

import com.example.jpa.domain.member.entity.Member;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post write(Member author, String title, String body) {

        // 1. Post 조립
        Post post = Post.builder()
                .author(author)
                .title(title)
                .body(body)
                .build();

        // 2. repository 에 넘김
        return postRepository.save(post);
    }

    @Transactional
    public Post modify(Post post, String newTitle, String newBody) {
        post.setTitle(newTitle);
        post.setBody(newBody);

        return post;
    }

    @Transactional //
    public void modify2(long id, String newTitle, String newBody) {
        Post post = postRepository.findById(id).get();
        post.setTitle(newTitle);
        post.setBody(newBody);
    }

    public long count() {
        return postRepository.count();
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public void deleteById(long id) {
        postRepository.deleteById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findByTitleAndBody(String title, String content) {
        return postRepository.findByTitleAndBody(title,content);
    }

    public List<Post> findByTitleLike(String title) {
        return postRepository.findByTitleLike(title);
    }

    public Page<Post> findByTitleLike(String title,Pageable pageable) {
        return postRepository.findByTitleLike(title,pageable);
    }

    public List<Post> findByOrderByIdDesc() {
        return postRepository.findByOrderByIdDesc();
    }

    public List<Post> findTop2ByTitleOrderByIdDesc(String title) {
        return postRepository.findTop2ByTitleOrderByIdDesc(title);
    }

    public List<Post> findByAuthorUsername(String user) {
        return postRepository.findByAuthorUsername(user);
    }
}
